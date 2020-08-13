package com.oocl.parkingsmart.websocket.protocol.data;

import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class PagePersonalRequest implements Data{
    private String latitude;
    private String longitude;
    private String startTime;
    private String endTime;
}
