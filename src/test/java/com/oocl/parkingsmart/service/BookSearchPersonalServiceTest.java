package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.impl.BookSearchPersonalCarPortServiceImpl;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class BookSearchPersonalServiceTest {
    @Test
    void should_return_personal_parking_space_when_hit_search_given_times_longitude_latitude() throws ParseException {
        //given
        RentOrderRepository mockRentOrderRepository = mock(RentOrderRepository.class);
        //when
        BookSearchPersonalCarPortService bookSearchPersonalCarPortService = new BookSearchPersonalCarPortServiceImpl(mockRentOrderRepository);
        PageRequest request = new PageRequest("113.574524", "22.373737", "2020-05-14 00:00:00", "2020-08-14 00:00:00");
        Calendar calendar1 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar1.set(2020, Calendar.JULY, 16,0,0,0);
        Calendar calendar2 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar2.set(2020, Calendar.AUGUST, 14,0,0,0);
        RentOrder rentOrder = new RentOrder(1, "CW-110", 1, calendar1.getTime(), calendar2.getTime(), 10d,
                "published", "a", "123456", "南方软件园", 113.299316, 22.227351);
        List<RentOrder> rentOrders = Collections.singletonList(rentOrder);
        given(mockRentOrderRepository.findAllNearbyPersonalCarPort(Double.parseDouble(request.getLongitude()), Double.parseDouble(request.getLatitude()))).willReturn(rentOrders);
        //then
        List<RentOrder> nearByRentOrders = bookSearchPersonalCarPortService.findNearbyCarPort(request);
        assertIterableEquals(rentOrders, nearByRentOrders);
    }

}
