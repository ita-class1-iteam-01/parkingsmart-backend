package com.oocl.parkingsmart.mq.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    public final static String QUEUE = "SECKILL_QUEUE";
    Integer rentId;
    Integer userId;
}
