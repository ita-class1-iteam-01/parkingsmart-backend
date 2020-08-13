package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.Enum.RentOrderEnum;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.BookSearchPersonalCarPortService;
import com.oocl.parkingsmart.websocket.protocol.data.PagePersonalRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookSearchPersonalCarPortServiceImpl implements BookSearchPersonalCarPortService {
    private final RentOrderRepository rentOrderRepository;

    public BookSearchPersonalCarPortServiceImpl(RentOrderRepository rentOrderRepository) {
        this.rentOrderRepository = rentOrderRepository;
    }

    @Override
    public List<RentOrder> findNearbyCarPort(PagePersonalRequest request) throws ParseException {
        Double latitude = Double.parseDouble(request.getLatitude());
        Double longitude = Double.parseDouble(request.getLongitude());
        List<RentOrder> rentOrders = rentOrderRepository.findAllNearbyPersonalCarPort(longitude, latitude);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date startTime = simpleDateFormat.parse(request.getStartTime());
        Date endTime = simpleDateFormat.parse(request.getEndTime());
        return rentOrders.stream().filter(rentOrder ->
                rentOrder.getStatus().equals(RentOrderEnum.PUBLISHED.getValue())
                && startTime.getTime()>=rentOrder.getRentStartDate().getTime()
                && endTime.getTime()<=rentOrder.getRentEndDate().getTime()
        ).collect(Collectors.toList());
    }
}
