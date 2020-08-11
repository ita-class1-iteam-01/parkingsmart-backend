package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.CarSpace;
import com.oocl.parkingsmart.repository.CarSpaceRepository;
import com.oocl.parkingsmart.service.CarSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarSpaceServiceImpl implements CarSpaceService {
    private final CarSpaceRepository carSpaceRepository;

    public CarSpaceServiceImpl(CarSpaceRepository carSpaceRepository) {
        this.carSpaceRepository = carSpaceRepository;
    }

    @Override
    public CarSpace getOneCarSpace(Integer parkingLotId) {
        return carSpaceRepository.findByParkingLotId(parkingLotId);
    }
}
