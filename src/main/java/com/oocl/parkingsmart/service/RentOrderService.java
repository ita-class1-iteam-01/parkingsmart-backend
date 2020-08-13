package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.RentOrder;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RentOrderService{

    List<RentOrder> getAll(Pageable pageable);

    RentOrder create(RentOrder rentOrder);

    RentOrder findById(Integer id);
}
