package com.oocl.parkingsmart.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.parkingsmart.websocket.protocol.Packet;
import com.oocl.parkingsmart.websocket.protocol.data.Data;
import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import com.oocl.parkingsmart.websocket.protocol.data.PageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.oocl.parkingsmart.websocket.protocol.command.Command.PAGE_REQUEST;
import static com.oocl.parkingsmart.websocket.protocol.command.Command.PAGE_RESPONSE;

@Component
@Slf4j
@ServerEndpoint("/parkingws")
public class WebSocketServer {

    private final Map<Integer, Class<? extends Data>> packetTypeMap;
    private static Gson gson = new GsonBuilder().create();
    private Session session;
    public WebSocketServer() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(PAGE_REQUEST, PageRequest.class);
        packetTypeMap.put(PAGE_RESPONSE, PageResponse.class);
    }
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        log.info("新连接加入");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Packet packet = gson.fromJson(message, Packet.class);
        Class<? extends Data> dataType = packetTypeMap.get(packet.getCommand());
        Data data = gson.fromJson(packet.getData(),dataType);
        Packet backPacket = null;
        if(data instanceof PageRequest){
            backPacket = handlerPageRequest((PageRequest)data);
        }
        this.sendMessage(gson.toJson(backPacket));
    }

    private Packet handlerPageRequest(PageRequest pageRequest) {
        //拿到数据
        String startTime = pageRequest.getStartTime();
        String endTime = pageRequest.getEndTime();
        String latitude = pageRequest.getLatitude();
        String longitude = pageRequest.getLongitude();
        //todo call method to get data
        Page<String> page = new PageImpl<>(Arrays.asList("test1","test2"));
        PageResponse response = new PageResponse();
        response.setPage(page);
        Packet packet = new Packet();
        packet.setData(gson.toJson(response));
        packet.setCommand(PAGE_RESPONSE);
        return packet;
    }
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
