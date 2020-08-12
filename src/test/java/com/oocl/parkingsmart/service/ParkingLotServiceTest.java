package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.repository.ParkingLotRepositoty;
import com.oocl.parkingsmart.service.impl.ParkingLotServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ParkingLotServiceTest {
    private ParkingLotService parkingLotService;
    private ParkingLotRepositoty mockRepository;
    private BookSearchService bookSearchService;

    @BeforeEach
    void init(){
        mockRepository = Mockito.mock(ParkingLotRepositoty.class);
        bookSearchService = Mockito.mock(BookSearchService.class);
        parkingLotService = new ParkingLotServiceImpl(mockRepository,bookSearchService);
    }
    @Test
    void should_return_parkingLot_when_select_on_given_parking_lot_id() {
        //given
        Integer parkingLotId = 1;
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(parkingLotId);
        parkingLot.setAddress("高级停车场");
        parkingLot.setPrice(3.22);
        parkingLot.setName("高级停车场");
        parkingLot.setLatitude(33.2);
        parkingLot.setLongitude(22.2);
        when(mockRepository.findById(parkingLotId)).thenReturn(Optional.of(parkingLot));
        //when
        ParkingLot one = parkingLotService.findOne(parkingLotId);
        //then
        assertNotNull(one);
        assertEquals(parkingLotId,one.getId());
    }

    @Test
    void should_return_parkingLot_with_remain_when_select__given_parking_lot_id_and_time(){
        //given
        Integer parkingLotId = 1;
        Integer parkingLotSize = 4;
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(parkingLotId);
        parkingLot.setAddress("高级停车场");
        parkingLot.setPrice(3.22);
        parkingLot.setName("高级停车场");
        parkingLot.setSize(parkingLotSize);
        parkingLot.setLatitude(33.2);
        parkingLot.setLongitude(22.2);
        Date startTime = new Date();
        Date endTime = new Date();
        when(mockRepository.findById(parkingLotId)).thenReturn(Optional.of(parkingLot));
        when(bookSearchService.getBookOrdersUsedByStartTimeAndEndTimeAndParkingLotId(parkingLotId,startTime,endTime)).thenReturn(Arrays.asList(new BookOrder(),new BookOrder()));
        ParkingLot one = parkingLotService.findOneByIdAndStartTimeAndEndTime(parkingLotId,startTime,endTime);
        //then
        assertNotNull(one);
        assertTrue(one.getSize()<parkingLotSize);
        assertEquals(parkingLotId,one.getId());
    }
}
