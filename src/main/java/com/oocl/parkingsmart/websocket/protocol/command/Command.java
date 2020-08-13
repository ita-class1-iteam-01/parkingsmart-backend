package com.oocl.parkingsmart.websocket.protocol.command;

public interface Command {
    Integer PAGE_REQUEST = 1;
    Integer PAGE_RESPONSE = 2;
    Integer PAGE_PERSONAL_REQUEST = 3;
    Integer PAGE_PERSONAL_RESPONSE = 4;
}
