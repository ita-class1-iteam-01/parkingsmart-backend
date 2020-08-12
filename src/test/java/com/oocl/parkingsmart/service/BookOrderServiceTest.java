package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.Enum.RentOrderEnum;
import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.impl.BookOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookOrderServiceTest {
    BookOrderService bookOrderService;
    BookOrderRepository bookOrderRepository;
    RentOrderRepository rentOrderRepository;

    @BeforeEach
    void init() {
        bookOrderRepository = Mockito.mock(BookOrderRepository.class);
        rentOrderRepository = Mockito.mock(RentOrderRepository.class);
        bookOrderService = new BookOrderServiceImpl(bookOrderRepository, rentOrderRepository);
    }

    @Test
    void should_create_book_order_when_create_given_book_order() {
        //given
        BookOrder order = new BookOrder();
        order.setId(1);
        order.setParkingId(1);
        order.setParkingType("Lots");
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2020, Calendar.DECEMBER, 1);
        order.setReservationStartTime(new Date());
        order.setReservationEndTime(calendar.getTime());
        order.setStatus("finish");

        //when
        when(bookOrderRepository.save(any())).thenReturn(order);
        BookOrder bookOrder = bookOrderService.create(order);

        //then
        assertNotNull(bookOrder);
        assertNotNull(bookOrder.getId());
        assertEquals(bookOrder.getParkingId(), order.getParkingId());
    }

    @Test
    void should_create_book_order_when_create_personal_order_given_personal_order() {
        //given
        BookOrder order = new BookOrder();
        order.setId(40);
        order.setParkingId(1);
        order.setParkingType("personal");
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2019, Calendar.JUNE, 1);
        order.setReservationStartTime(calendar.getTime());
        calendar.set(2020, Calendar.SEPTEMBER, 1);
        order.setReservationEndTime(calendar.getTime());
        order.setStatus("booked");
        order.setTotalPrice((double) 200);

        RentOrder rentOrder = new RentOrder();
        rentOrder.setAddress("广东省珠海市唐家湾惠景畅园");
        rentOrder.setLatitude(1.1);
        rentOrder.setLongitude(2.1);
        rentOrder.setContactNumber("13531915996");
        rentOrder.setContactPerson("quentin");
        rentOrder.setPersonCarport("CW-001");
        rentOrder.setPrice(200.00);
        rentOrder.setStatus(RentOrderEnum.PUBLISHED.getValue());
        rentOrder.setUserId(9);
        calendar.set(2019, Calendar.JUNE, 1);
        rentOrder.setRentStartDate(calendar.getTime());
        calendar.set(2020, Calendar.SEPTEMBER, 1);
        rentOrder.setRentEndDate(calendar.getTime());

        //when
        when(bookOrderRepository.save(order)).thenReturn(order);
        BookOrder bookOrder = bookOrderService.createPersonalOrder(order, rentOrder);

        //then
        assertNotNull(bookOrder);
        assertNotNull(bookOrder.getId());
        assertEquals(bookOrder.getParkingId(), order.getParkingId());
        assertEquals(3200, bookOrder.getTotalPrice());
    }
}