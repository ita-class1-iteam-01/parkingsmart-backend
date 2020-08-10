package com.oocl.parkingsmart.websocket.protocol.data;

@lombok.Data
public class PageRequest implements Data{
    private String latitude;
    private String longitude;
    private String startTime;
    private String endTime;
}
