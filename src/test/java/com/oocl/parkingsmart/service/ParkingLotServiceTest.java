package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.repository.ParkingLotRepositoty;
import com.oocl.parkingsmart.service.impl.ParkingLotServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotServiceTest {
    private ParkingLotService parkingLotService;
    private ParkingLotRepositoty parkingLotRepositoty;

    @BeforeEach
    void init(){
        parkingLotService = new ParkingLotServiceImpl();
    }
    @Test
    void should_return_parkingLot_when_select_on_given_parking_lot_id() {
        //given
        Integer parkingLotId = 1;
        //when
        ParkingLot one = parkingLotService.findOne(parkingLotId);
        //then
        assertNotNull(one);
        assertEquals(parkingLotId,one.getId());
    }
}
