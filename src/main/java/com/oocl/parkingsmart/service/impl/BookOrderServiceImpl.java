package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import com.oocl.parkingsmart.repository.RentOrderRepository;
import com.oocl.parkingsmart.service.BookOrderService;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
public class BookOrderServiceImpl implements BookOrderService {
    private BookOrderRepository bookOrderRepository;
    private RentOrderRepository rentOrderRepository;

    public BookOrderServiceImpl(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    public BookOrderServiceImpl(RentOrderRepository rentOrderRepository) {
        this.rentOrderRepository = rentOrderRepository;
    }

    public BookOrderServiceImpl(BookOrderRepository bookOrderRepository, RentOrderRepository rentOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
        this.rentOrderRepository = rentOrderRepository;
    }

    @Override
    public BookOrder create(BookOrder bookOrder) {
        return bookOrderRepository.save(bookOrder);
    }

    @Override
    public BookOrder createPersonalOrder(BookOrder order, RentOrder rentOrder) {
        if(rentOrder.getStatus().equals("published")){
            long months = ChronoUnit.MONTHS.between(rentOrder.getRentStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),rentOrder.getRentEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())+1;
            order.setTotalPrice(months*rentOrder.getPrice());
            BookOrder returnedOrder = bookOrderRepository.save(order);
            System.out.println(returnedOrder.getTotalPrice());
            rentOrder.setStatus("finished");
            rentOrderRepository.save(rentOrder);
            return returnedOrder;
        }
        return null;
    }
}
