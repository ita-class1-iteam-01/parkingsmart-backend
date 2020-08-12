package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.repository.ParkingLotRepositoty;
import com.oocl.parkingsmart.service.BookSearchService;
import com.oocl.parkingsmart.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepositoty repositoty;
    private final BookSearchService bookSearchService;

    public ParkingLotServiceImpl(ParkingLotRepositoty repositoty,BookSearchService bookSearchService) {
        this.repositoty = repositoty;
        this.bookSearchService = bookSearchService;
    }

    @Override
    public ParkingLot findOne(Integer parkingLotId) {
        return repositoty.findById(parkingLotId).orElse(null);
    }

    @Override
    public ParkingLot findOneByIdAndStartTimeAndEndTime(Integer parkingLotId, Date startTime, Date endTime) {
        Optional<ParkingLot> optional = repositoty.findById(parkingLotId);
        if(!optional.isPresent()){
            return null;
        }
        ParkingLot parkingLot = optional.get();
        int usedCarPortCount = bookSearchService.getBookOrdersUsedByStartTimeAndEndTimeAndParkingLotId(parkingLotId, startTime, endTime).size();
        parkingLot.setSize(parkingLot.getSize()-usedCarPortCount);
        return parkingLot;
    }
}
