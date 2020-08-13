package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.mq.message.Order;

import java.util.List;

public interface BookOrderService {
    BookOrder create(BookOrder bookOrder);

    BookOrder createPersonalOrder(BookOrder order, Integer rentOrderId);

    List<BookOrder> getAll();

    BookOrder seckill(Integer rentId);
}
