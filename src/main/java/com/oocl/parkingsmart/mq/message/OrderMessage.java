package com.oocl.parkingsmart.mq.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderMessage implements Serializable {
    public final static String QUEUE = "SECKILL_QUEUE";
    Integer RentId;
    Integer userId;
}
