package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;

import java.text.ParseException;
import java.util.List;

public interface BookSearchPersonalCarPortService {
    List<RentOrder> findNearbyCarPort(PageRequest request) throws ParseException;
}
