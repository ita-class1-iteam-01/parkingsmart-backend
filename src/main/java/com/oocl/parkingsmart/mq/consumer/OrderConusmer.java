package com.oocl.parkingsmart.mq.consumer;

import com.google.gson.Gson;
import com.oocl.parkingsmart.Enum.BookOrderEnum;
import com.oocl.parkingsmart.Enum.RentOrderEnum;
import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.mq.message.Order;
import com.oocl.parkingsmart.service.BookOrderService;
import com.oocl.parkingsmart.service.RentOrderService;
import com.oocl.parkingsmart.websocket.WebSocketServer;
import com.oocl.parkingsmart.websocket.protocol.data.PagePersonalRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.oocl.parkingsmart.mq.message.Order.QUEUE;

@Component
@Slf4j
public class OrderConusmer {

    @Autowired
    BookOrderService bookOrderService;
    @Autowired
    RentOrderService rentOrderService;

    @Autowired
    WebSocketServer webSocketServer;

    @JmsListener(destination = QUEUE)
    public void onMessage(String message) throws IOException, ParseException {
        log.info("get message:{}", message);
        BookOrder bookOrder = new BookOrder();
        Gson gson = new Gson();
        Order order = gson.fromJson(message,Order.class);
        Integer rentId = order.getRentId();
        RentOrder rentOrder = rentOrderService.findById(rentId);
        if(rentOrder.getStatus().equals(RentOrderEnum.BOOKED.getValue())){
            return;
        }

        long months = ChronoUnit.MONTHS.between(rentOrder.getRentStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),rentOrder.getRentEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())+1;
        bookOrder.setTotalPrice(months*rentOrder.getPrice());
        bookOrder.setAddress(rentOrder.getAddress());
        bookOrder.setParkingType("personal");
        bookOrder.setStatus(BookOrderEnum.BOOKED.getValue());
        bookOrder.setCarPort(rentOrder.getPersonCarport());
        bookOrder.setUserId(order.getUserId());
        bookOrder.setReservationStartTime(rentOrder.getRentStartDate());
        bookOrder.setReservationEndTime(rentOrder.getRentEndDate());
        bookOrder.setParkingId(rentOrder.getId());
        if(bookOrderService.create(bookOrder)!=null){
            rentOrder.setStatus(RentOrderEnum.BOOKED.getValue());
            rentOrderService.updateRentOrder(rentOrder);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDateTime = format.format(new Date(Long.parseLong(order.getReservationStartTime())));
            String endDateTime = format.format(new Date(Long.parseLong(order.getReservationEndTime())));
            PagePersonalRequest pageRequest = new PagePersonalRequest(order.getLatitude(),order.getLongitude(),startDateTime,endDateTime);
            webSocketServer.sendPersonList(order.getUserId(),pageRequest);
            return;
        }
    }
}
