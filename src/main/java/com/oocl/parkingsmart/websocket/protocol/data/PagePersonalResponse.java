package com.oocl.parkingsmart.websocket.protocol.data;


import com.oocl.parkingsmart.entity.RentOrder;

import java.util.List;

@lombok.Data
public class PagePersonalResponse implements Data {
    private List<RentOrder> pagePersonal;
}
