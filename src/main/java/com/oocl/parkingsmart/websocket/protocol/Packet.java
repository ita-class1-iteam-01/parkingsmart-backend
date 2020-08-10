package com.oocl.parkingsmart.websocket.protocol;

import lombok.Data;

@Data
public class Packet {

    private Byte version = 1;
    private int command;
    private String data;
}
