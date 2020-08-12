package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;

public interface BookOrderService {
    BookOrder create(BookOrder bookOrder);

    BookOrder createPersonalOrder(BookOrder order, Integer rentOrderId);
}
