package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.impl.BookOrderServiceImpl;
import com.oocl.parkingsmart.service.impl.RentOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
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
}
