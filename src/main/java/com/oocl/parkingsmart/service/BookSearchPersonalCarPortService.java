package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;

import java.util.List;

public interface BookSearchPersonalCarPortService {
    List<RentOrder> findNearbyCarPort(PageRequest request);
}
