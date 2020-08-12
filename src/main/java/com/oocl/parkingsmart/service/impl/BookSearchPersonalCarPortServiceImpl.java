package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.BookSearchPersonalCarPortService;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;

import java.util.List;

public class BookSearchPersonalCarPortServiceImpl implements BookSearchPersonalCarPortService {
    private final RentOrderRepository rentOrderRepository;

    public BookSearchPersonalCarPortServiceImpl(RentOrderRepository rentOrderRepository) {
        this.rentOrderRepository = rentOrderRepository;
    }

    @Override
    public List<RentOrder> findNearbyCarPort(PageRequest request) {
        return null;
    }
}
