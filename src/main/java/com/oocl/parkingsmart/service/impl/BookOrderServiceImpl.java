package com.oocl.parkingsmart.service.impl;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.repository.BookOrderRepository;
import com.oocl.parkingsmart.service.BookOrderService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BookOrderServiceImpl implements BookOrderService {
    private final BookOrderRepository bookOrderRepository;

    public BookOrderServiceImpl(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    @Override
    public BookOrder create(BookOrder bookOrder) {
        return bookOrderRepository.save(bookOrder);
    }
}
