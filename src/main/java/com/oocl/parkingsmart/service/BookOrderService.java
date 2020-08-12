package com.oocl.parkingsmart.service;

import com.oocl.parkingsmart.entity.BookOrder;
import com.oocl.parkingsmart.entity.RentOrder;

public interface BookOrderService {
    BookOrder create(BookOrder bookOrder);

    BookOrder createPersonalOrder(BookOrder order, RentOrder rentOrder);
}
