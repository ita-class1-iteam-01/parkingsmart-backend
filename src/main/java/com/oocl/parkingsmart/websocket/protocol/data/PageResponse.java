package com.oocl.parkingsmart.websocket.protocol.data;

import com.oocl.parkingsmart.model.ParkingLot;

import java.util.List;

@lombok.Data
public class PageResponse implements Data {
    private List<ParkingLot> page;
}
