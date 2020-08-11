package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.CarSpace;
import com.oocl.parkingsmart.service.impl.CarSpaceServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarSpaceServiceTest {

    @Test
    void should_return_car_space_when_get_car_space_given_parking_lot_id() {
        //given
        Integer parkingLotId = 1;
        CarSpaceService carSpaceService = new CarSpaceServiceImpl();
        //when
        CarSpace oneCarSpace = carSpaceService.getOneCarSpace(parkingLotId);
        //then
        assertNotNull(oneCarSpace);
    }
}
