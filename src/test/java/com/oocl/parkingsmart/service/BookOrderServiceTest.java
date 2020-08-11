package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class BookOrderServiceTest {
    BookOrderService bookOrderService;

    @BeforeEach
    void init(){
        bookOrderService = new BookOrderServiceImpl();
    }
    @Test
    void should_create_book_order_when_create_given_book_order() {
        //given
        BookOrder order = new BookOrder();
        order.setParkingId(1);
        order.setParkingType("Lots");
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2020, Calendar.DECEMBER, 1);
        order.setReservationStartTime(new Date());
        order.setReservationEndTime(calendar.getTime());
        order.setStatus("Finish");
        //when
        BookOrder bookOrder = bookOrderService.create(order);
        //then
        assertNotNull(bookOrder);
        assertNotNull(bookOrder.getId());
        assertEquals(bookOrder.getParkingId(),order.getParkingId());
    }
}