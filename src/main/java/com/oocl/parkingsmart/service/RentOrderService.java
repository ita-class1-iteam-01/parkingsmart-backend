package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.RentOrder;

import java.util.List;

public interface RentOrderService{

    List<RentOrder> getAll();

    RentOrder create(RentOrder rentOrder);

}
