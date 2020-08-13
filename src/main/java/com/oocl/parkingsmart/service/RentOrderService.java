package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.RentOrder;

import java.util.List;
import java.util.Optional;

public interface RentOrderService{

    List<RentOrder> getAll();

    RentOrder create(RentOrder rentOrder);

    RentOrder findById(Integer id);

    RentOrder updateRentOrder(RentOrder rentOrder);
}
