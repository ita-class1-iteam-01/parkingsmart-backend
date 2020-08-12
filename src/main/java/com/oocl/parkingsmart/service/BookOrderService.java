package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;

import java.util.List;

public interface BookOrderService {
    BookOrder create(BookOrder bookOrder);

    BookOrder createPersonalOrder(BookOrder order, Integer rentOrderId);

    List<BookOrder> getAll();

}
