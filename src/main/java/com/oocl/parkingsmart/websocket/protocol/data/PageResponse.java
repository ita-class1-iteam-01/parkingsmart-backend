package com.oocl.parkingsmart.websocket.protocol.data;


import org.springframework.data.domain.Page;

@lombok.Data
public class PageResponse implements Data {
    private Page page;
}
