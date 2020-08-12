package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.impl.BookSearchPersonalCarPortServiceImpl;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class BookSearchPersonalServiceTest {
    @Test
    void should_return_personal_parking_space_when_hit_search_given_times_longitude_latitude() {
        //given
        RentOrderRepository mockRentOrderRepository = mock(RentOrderRepository.class);
        //when
        BookSearchPersonalCarPortService bookSearchPersonalCarPortService = new BookSearchPersonalCarPortServiceImpl(mockRentOrderRepository);
        PageRequest request = new PageRequest("113.574524", "22.373737", "2020-08-14 18:40:22", "2020-08-14 18:40:22");
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2019, Calendar.JUNE, 1);
        RentOrder rentOrder = new RentOrder(1, "CW-110", 1, calendar.getTime(), calendar.getTime(), 10d, "finished", "a", "123456", "南方软件园", 113.299316, 22.227351);
        List<RentOrder> rentOrders = Collections.singletonList(rentOrder);
        given(mockRentOrderRepository.findAllNearbyPersonalCarPort(Double.parseDouble(request.getLongitude()), Double.parseDouble(request.getLatitude()))).willReturn(rentOrders);
        //then
        List<RentOrder> nearByRentOrders = bookSearchPersonalCarPortService.findNearbyCarPort(request);
        assertIterableEquals(rentOrders, nearByRentOrders);
    }

}
