package com.oocl.parkingsmart.mq.consumer;

import com.oocl.parkingsmart.Enum.BookOrderEnum;
import com.oocl.parkingsmart.Enum.ParkingLotTypeEnum;
import com.oocl.parkingsmart.Enum.RentOrderEnum;
import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.mq.message.OrderMessage;
import com.oocl.parkingsmart.service.BookOrderService;
import com.oocl.parkingsmart.service.RentOrderService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.oocl.parkingsmart.mq.message.OrderMessage.QUEUE;

@Component
@Slf4j
public class OrderConusmer {

//    @Autowired
//    BookOrderService bookOrderService;
//    @Autowired
//    RentOrderService rentOrderService;
//    @JmsListener(destination = QUEUE)
//    public void onMessage(OrderMessage message) {
//        //get message, call service method to save order to DB
//        log.info("get message:{}", message);
//        BookOrder bookOrder = new BookOrder();
//        Integer rentId = message.getRentId();
//        RentOrder rentOrder = rentOrderService.findById(rentId);
//        if(rentOrder.getStatus().equals(RentOrderEnum.BOOKED.getValue())){
//            return;
//        }
//        //copy from rentOrder
//        Date reservationStartTime = bookOrder.getReservationStartTime();
//        Date reservationEndTime = bookOrder.getReservationEndTime();
//        long to = reservationEndTime.getTime();
//        long from = reservationStartTime.getTime();
//        int hours = (int)((to-from)/(1000*60*60));
//        bookOrder.setTotalPrice(hours*rentOrder.getPrice());
//        bookOrder.setAddress(rentOrder.getAddress());
//        bookOrder.setParkingType(ParkingLotTypeEnum.PERSONAL.getValue());
//        bookOrder.setStatus(BookOrderEnum.BOOKED.getValue());
//        bookOrder.setCarPort(rentOrder.getPersonCarport());
//        bookOrder.setUserId(message.getUserId());
//        bookOrder.setReservationStartTime(rentOrder.getRentStartDate());
//        bookOrder.setReservationEndTime(rentOrder.getRentEndDate());
//        bookOrder.setParkingId(rentOrder.getId());
//        if(bookOrderService.create(bookOrder)!=null){
//            //update
//            rentOrder.setStatus(RentOrderEnum.PUBLISHED.getValue());
//            rentOrderService.updateRentOrder(rentOrder);
//            return;
//        }
//    }
}
