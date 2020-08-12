package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import com.oocl.parkingsmart.service.impl.BookOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookOrderServiceTest {
    BookOrderService bookOrderService;
    BookOrderRepository bookOrderRepository;

    @BeforeEach
    void init(){
        bookOrderRepository = Mockito.mock(BookOrderRepository.class);
        BookOrder order = new BookOrder();
        order.setParkingId(1);
        order.setParkingType("Lots");
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.set(2020, Calendar.DECEMBER, 1);
        order.setReservationStartTime(new Date());
        order.setReservationEndTime(calendar.getTime());
        order.setStatus("finish");
        order.setId(1);
        when(bookOrderRepository.save(any())).thenReturn(order);
        bookOrderService = new BookOrderServiceImpl(bookOrderRepository);
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
        order.setStatus("finish");
        //when
        BookOrder bookOrder = bookOrderService.create(order);
        //then
        assertNotNull(bookOrder);
        assertNotNull(bookOrder.getId());
        assertEquals(bookOrder.getParkingId(),order.getParkingId());
    }

    @Test
    void should_return_book_orders_when_get_all_given_no_parameter() {
        //given
        List<BookOrder> returnedOrders = Arrays.asList(new BookOrder(), new BookOrder(), new BookOrder());

        //when
        List<BookOrder> bookOrders = bookOrderService.getAll();
        when(bookOrderRepository.findAll()).thenReturn(returnedOrders);

        //then
        assertNotNull(bookOrders);
    }
}