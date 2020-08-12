package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.BookOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class BookOrderServiceImpl implements BookOrderService {
    private final BookOrderRepository bookOrderRepository;
    private final RentOrderRepository rentOrderRepository;

    public BookOrderServiceImpl(BookOrderRepository bookOrderRepository, RentOrderRepository rentOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
        this.rentOrderRepository = rentOrderRepository;
    }

    @Override
    public BookOrder create(BookOrder bookOrder) {
        return bookOrderRepository.save(bookOrder);
    }

    @Override
    @Transactional
    public BookOrder createPersonalOrder(BookOrder order, Integer rentOrderId) {
        Optional<RentOrder> rentOrder = rentOrderRepository.findById(rentOrderId);
        if(!rentOrder.isPresent()||rentOrder.get().getStatus().equals("booked")) {
            return null;
        }
        long months = ChronoUnit.MONTHS.between(rentOrder.get().getRentStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),rentOrder.get().getRentEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())+1;
        order.setTotalPrice(months*rentOrder.get().getPrice());
        order.setStatus("booked");
        BookOrder returnOrder = bookOrderRepository.save(order);
        rentOrder.get().setStatus("booked");
        rentOrderRepository.save(rentOrder.get());
        return returnOrder;
    }
}
