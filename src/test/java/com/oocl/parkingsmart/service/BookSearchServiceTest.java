package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import com.oocl.parkingsmart.repository.ParkingLotRepositoty;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class BookSearchServiceTest {
    @Test
    void should_return_parkingLotList_when_hit_search_given_times_longitude_latitude() throws ParseException {
        //given
        ParkingLotRepositoty mockParkingLotRepository = mock(ParkingLotRepositoty.class);
        BookOrderRepository mockBookOrderRepository =mock(BookOrderRepository.class);
        //when
        PageRequest request = new PageRequest("113.574524", "22.373737", "2020-08-14 18:40:22", "2020-08-14 18:40:22");
        ParkingLot parkingLot = new ParkingLot(1, "123", 10, 10d, "123");
        List<ParkingLot> parkingLots = Collections.singletonList(parkingLot);
        BookSearchService bookSearchService = new BookSearchService(mockParkingLotRepository,mockBookOrderRepository);
        given(mockParkingLotRepository.findAllNearbyParkingLot(Double.parseDouble(request.getLongitude()),Double.parseDouble(request.getLatitude()))).willReturn(parkingLots);
        given(mockBookOrderRepository.findByStatusNotFINISHED(1)).willReturn(Collections.singletonList(new BookOrder()));
        //then
        List<ParkingLot> nearbyParkingLots = bookSearchService.findNearbyParkingLot(request);
        assertIterableEquals(parkingLots, nearbyParkingLots);
    }

    @Test
    void should_return_noData_when_hit_search_given_times_longitude_latitude() throws ParseException {
        //given
        ParkingLotRepositoty mockParkingLotRepository = mock(ParkingLotRepositoty.class);
        BookOrderRepository mockBookOrderRepository =mock(BookOrderRepository.class);
        //when
        PageRequest request = new PageRequest("113.574524", "22.373737", "2020-08-14 18:40:22", "2020-08-14 18:40:22");
        BookSearchService bookSearchService = new BookSearchService(mockParkingLotRepository,mockBookOrderRepository);
        given(mockParkingLotRepository.findAllNearbyParkingLot(Double.parseDouble(request.getLongitude()),Double.parseDouble(request.getLatitude()))).willReturn(null);
        given(mockBookOrderRepository.findByStatusNotFINISHED(null)).willReturn(Collections.singletonList(new BookOrder()));
        //then
        List<ParkingLot> nearbyParkingLots = bookSearchService.findNearbyParkingLot(request);
        assertIterableEquals(null, nearbyParkingLots);
    }


}
