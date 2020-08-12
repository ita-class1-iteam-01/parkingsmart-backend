package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.CarSpace;
import com.oocl.parkingsmart.repository.CarSpaceRepository;
import com.oocl.parkingsmart.service.BookSearchService;
import com.oocl.parkingsmart.service.CarSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarSpaceServiceImpl implements CarSpaceService {
    private final CarSpaceRepository carSpaceRepository;

    @Autowired
    private BookSearchService bookSearchService;
    public CarSpaceServiceImpl(CarSpaceRepository carSpaceRepository) {
        this.carSpaceRepository = carSpaceRepository;
    }

    @Override
    public CarSpace getOneCarSpace(Integer parkingLotId) {
        return carSpaceRepository.findByParkingLotId(parkingLotId);
    }

    @Override
    public List<CarSpace> getUnUsedCarSpaces(Integer parkingLotId, Date startTime, Date endTime) {
        List<BookOrder> bookOrders = bookSearchService.getBookOrdersUsedByStartTimeAndEndTimeAndParkingLotId(parkingLotId, startTime, endTime);
        List<CarSpace> allByParkingLotId = carSpaceRepository.findAllByParkingLotId(parkingLotId);
        HashSet<String> carPortSet = new HashSet<>(bookOrders.stream().map(BookOrder::getCarPort).collect(Collectors.toList()));
        return allByParkingLotId.stream().filter(carSpace -> !carPortSet.contains(carSpace.getCarPort())).collect(Collectors.toList());
    }

}
