package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.CarSpace;

import java.util.Date;
import java.util.List;

public interface CarSpaceService {
    CarSpace getOneCarSpace(Integer ParkingLotId);

    List<CarSpace> getUnUsedCarSpaces(Integer parkingLotId,Date startTime,Date endTime);
}
