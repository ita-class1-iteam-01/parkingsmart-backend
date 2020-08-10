package com.oocl.parkingsmart.websocket.codeC;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.parkingsmart.websocket.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class WebSocketProtocolCodeC extends MessageToMessageCodec<TextWebSocketFrame, Packet> {

    static Gson gson = new GsonBuilder().create();
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        out.add(new TextWebSocketFrame(gson.toJson(msg)));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
        out.add(gson.fromJson(msg.text(),Packet.class));
    }
}
