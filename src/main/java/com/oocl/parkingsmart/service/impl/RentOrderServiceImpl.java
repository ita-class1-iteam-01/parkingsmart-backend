package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.RentOrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public RentOrder create(RentOrder rentOrder) {
        return rentOrderRepository.save(rentOrder);
    }

    @Override
    public RentOrder findById(Integer id) {
        return rentOrderRepository.findById(id).orElse(null);
    }
}
