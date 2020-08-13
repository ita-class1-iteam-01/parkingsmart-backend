package com.oocl.parkingsmart.mq.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    public final static String QUEUE = "SECKILL_QUEUE";
    Integer rentId;
    Integer userId;
    private String latitude;
    private String longitude;
    private String reservationStartTime;
    private String reservationEndTime;
}
