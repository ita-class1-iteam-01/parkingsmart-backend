package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.RentOrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RentOrderServiceImpl implements RentOrderService {

    @Autowired
    private RentOrderRepository rentOrderRepository;

    @Override
    public List<RentOrder> getAll() {
        return rentOrderRepository.findAll();
    }

}
