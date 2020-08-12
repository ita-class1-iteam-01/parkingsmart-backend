package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.Enum.RentOrderEnum;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.impl.RentOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RentOrderServiceTest {

    @Autowired
    RentOrderService rentOrderService;
    RentOrderRepository rentOrderRepository;

    @BeforeEach
    void init() {
        rentOrderRepository = mock(RentOrderRepository.class);
        rentOrderService = new RentOrderServiceImpl(rentOrderRepository);
    }

    @Test
    void should_return_rent_orders_when_get_all_given() {
        //given
        List<RentOrder> returnedOrders = Arrays.asList(new RentOrder(), new RentOrder(), new RentOrder());

        //when
        List<RentOrder> rentOrders = rentOrderService.getAll();
        when(rentOrderRepository.findAll()).thenReturn(returnedOrders);

        //then
        assertNotNull(rentOrders);
    }

    @Test
    void should_create_rent_order_when_create_order_given_order() {
        //given
        RentOrder order = new RentOrder();
        order.setAddress("广东省珠海市唐家湾惠景畅园");
        order.setLatitude(1.1);
        order.setLongitude(2.1);
        order.setContactNumber("13531915996");
        order.setContactPerson("henry");
        order.setPersonCarport("CW-001");
        order.setPrice(200.00);
        order.setStatus(RentOrderEnum.PUBLISHED.getValue());
        order.setUserId(1);
        order.setRentStartDate(new Date());
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+8"));
        calendar.set(2020, Calendar.DECEMBER, 1);
        order.setRentEndDate(calendar.getTime());
        when(rentOrderRepository.save(any())).thenReturn(order);
        //when
        RentOrder rentOrder = rentOrderService.create(order);
        //then
        assertNotNull(rentOrder);
    }
}
