package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.CarSpace;
import com.oocl.parkingsmart.service.impl.CarSpaceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CarSpaceServiceTest {

    @Autowired
    CarSpaceService carSpaceService;
    @Test
    void should_return_car_space_when_get_car_space_given_parking_lot_id() {
        //given
        Integer parkingLotId = 1;
        //when
        CarSpace oneCarSpace = carSpaceService.getOneCarSpace(parkingLotId);
        //then
        assertNotNull(oneCarSpace);
    }
}
