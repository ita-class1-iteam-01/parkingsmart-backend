package com.oocl.parkingsmart.websocket.codeC;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.parkingsmart.websocket.protocol.Packet;
import com.oocl.parkingsmart.websocket.protocol.data.Data;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import com.oocl.parkingsmart.websocket.protocol.data.PageResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.oocl.parkingsmart.websocket.protocol.command.Command.PAGE_REQUEST;
import static com.oocl.parkingsmart.websocket.protocol.command.Command.PAGE_RESPONSE;

@Component
@ChannelHandler.Sharable
public class PacketToDataCodeC extends MessageToMessageCodec<Packet, Data> {
    private final Map<Integer, Class<? extends Data>> packetTypeMap;
    private static Gson gson = new GsonBuilder().create();

    public PacketToDataCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(PAGE_REQUEST, PageRequest.class);
        packetTypeMap.put(PAGE_RESPONSE, PageResponse.class);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Data data, List<Object> out) throws Exception {
        Packet packet = new Packet();
        if(data instanceof PageResponse){
            packet.setData(gson.toJson(data));
            packet.setCommand(PAGE_RESPONSE);
        }
        out.add(packet);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet packet, List<Object> out) throws Exception {
        Class<? extends Data> dataType = packetTypeMap.get(packet.getCommand());
        Data data = gson.fromJson(packet.getData(),dataType);
        out.add(data);
    }
}
