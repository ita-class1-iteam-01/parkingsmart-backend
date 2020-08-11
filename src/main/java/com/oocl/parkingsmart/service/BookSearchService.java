package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.repository.ParkingLotRepositoty;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;

import java.util.List;

public class BookSearchService {
    private ParkingLotRepositoty parkingLotRepositoty;

    public BookSearchService(ParkingLotRepositoty parkingLotRepository) {
        this.parkingLotRepositoty = parkingLotRepository;
    }

    public List<ParkingLot> findNearbyParkingLot(PageRequest pageRequest) {

        return parkingLotRepositoty.findNearbyParkingLot(pageRequest);
    }


}
