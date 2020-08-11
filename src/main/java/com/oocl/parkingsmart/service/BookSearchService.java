package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.repository.ParkingLotRepositoty;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookSearchService {
    @Autowired
    private ParkingLotRepositoty parkingLotRepositoty;

    public BookSearchService() {
    }

    public BookSearchService(ParkingLotRepositoty parkingLotRepository) {
        this.parkingLotRepositoty = parkingLotRepository;
    }

    public List<ParkingLot> findNearbyParkingLot(PageRequest pageRequest) {
        Double longitude = Double.valueOf(pageRequest.getLongitude());
        Double latitude = Double.valueOf(pageRequest.getLatitude());
        System.out.println(longitude);
        System.out.println(latitude);
        return parkingLotRepositoty.findAllNearbyParkingLot(longitude,latitude);
    }


}
