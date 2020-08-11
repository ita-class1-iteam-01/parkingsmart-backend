package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import org.springframework.stereotype.Component;

@Component
public class BookOrderServiceImpl implements BookOrderService{
    private final BookOrderRepository bookOrderRepository;

    public BookOrderServiceImpl(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    @Override
    public BookOrder create(BookOrder bookOrder) {
        return bookOrderRepository.save(bookOrder);
    }
}
