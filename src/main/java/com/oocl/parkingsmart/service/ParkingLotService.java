package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.model.ParkingLot;

import java.util.Date;

public interface ParkingLotService {
    ParkingLot findOne(Integer parkingLotId);

    ParkingLot findOneByIdAndStartTimeAndEndTime(Integer parkingLotId, Date startTime, Date endTime);
}
