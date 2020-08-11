package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.CarSpace;
import com.oocl.parkingsmart.service.impl.CarSpaceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CarSpaceServiceTest {

    @Autowired
    CarSpaceService carSpaceService;

    @Test
    void should_return_carSpace_not_used_when_get_not_used_car_given_parking_lot_id_and_time() {
        //given
        Integer parkingLotId = 1;
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2020, Calendar.SEPTEMBER, 15);
        //when
        List<CarSpace> unUsedCarSpaces = carSpaceService.getUnUsedCarSpaces(parkingLotId, new Date(), calendar.getTime());
        //then
        assertNotNull(unUsedCarSpaces);
    }
}
