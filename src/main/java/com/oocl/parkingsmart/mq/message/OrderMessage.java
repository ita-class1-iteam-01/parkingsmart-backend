package com.oocl.parkingsmart.mq.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderMessage implements Serializable {
    public final static String QUEUE = "ORDER_QUEUE";
    String userId;
    String parkingLotId;
    String startTime;
    String endTime;
}
