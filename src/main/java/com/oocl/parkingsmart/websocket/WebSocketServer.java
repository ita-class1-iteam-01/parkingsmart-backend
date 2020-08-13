package com.oocl.parkingsmart.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.parkingsmart.componment.MyApplicationContextAware;
import com.oocl.parkingsmart.entity.RentOrder;
import com.oocl.parkingsmart.model.ParkingLot;
import com.oocl.parkingsmart.service.BookSearchPersonalCarPortService;
import com.oocl.parkingsmart.service.BookSearchService;
import com.oocl.parkingsmart.websocket.protocol.Packet;
import com.oocl.parkingsmart.websocket.protocol.data.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.oocl.parkingsmart.websocket.protocol.command.Command.*;

@Component
@Slf4j
@ServerEndpoint("/parkingws/{userId}")
public class WebSocketServer {

    private final Map<Integer, Class<? extends Data>> packetTypeMap;
    private static Gson gson = new GsonBuilder().create();
    private Session session;
    private BookSearchService bookSearchService = (BookSearchService) MyApplicationContextAware.getApplicationContext().getBean("BookSearchService");
    private final BookSearchPersonalCarPortService bookSearchPersonalCarPortService= (BookSearchPersonalCarPortService) MyApplicationContextAware.getApplicationContext().getBean("BookSearchPersonalCarPortService");
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private static ConcurrentHashMap<Integer, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Integer userId;

    public WebSocketServer() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(PAGE_REQUEST, PageRequest.class);
        packetTypeMap.put(PAGE_RESPONSE, PageResponse.class);
        packetTypeMap.put(PAGE_PERSONAL_REQUEST, PagePersonalRequest.class);
        packetTypeMap.put(PAGE_PERSONAL_RESPONSE, PagePersonalResponse.class);
    }

    @OnOpen
    public void onOpen(Session session,@PathParam("userId") Integer userId){
        this.session = session;
        this.userId = userId;

        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            log.info("[WebSocketServer] open a webSocket, userId = {}", userId);
        } else {
            webSocketMap.put(userId, this);
            log.info("[WebSocketServer] open a webSocket, userId = {}", userId);
            onlineCount.incrementAndGet();
        }
        log.info("新连接加入");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, ParseException {
        log.info("message: {}", message);
        Packet packet = gson.fromJson(message, Packet.class);
        Class<? extends Data> dataType = packetTypeMap.get(packet.getCommand());
        Data data = gson.fromJson(packet.getData(), dataType);
        Packet backPacket = null;
        if (data instanceof PageRequest) {
            backPacket = handlerPageRequest((PageRequest) data);
        }else if(data instanceof PagePersonalRequest) {
            backPacket = handlerPagePersonalRequest((PagePersonalRequest) data);
        }
        this.sendMessage(gson.toJson(backPacket));
    }

    private Packet handlerPagePersonalRequest(PagePersonalRequest pagePersonalRequest) throws ParseException {
        List<RentOrder> nearbyCarPort = bookSearchPersonalCarPortService.findNearbyCarPort(pagePersonalRequest);
        nearbyCarPort = nearbyCarPort.stream().sorted((order1,order2)->{
            if(order1.getSeckilling() && !order2.getSeckilling()){
                return 1;
            }else if(!order1.getSeckilling() && !order2.getSeckilling()){
                return order1.getCreationTime().compareTo(order1.getCreationTime());
            } else if(!order1.getSeckilling() && order2.getSeckilling()){
                return -1;
            } else {
                return order1.getCreationTime().compareTo(order1.getCreationTime());
            }
        }).collect(Collectors.toList());
        PagePersonalResponse response = new PagePersonalResponse();
        response.setPagePersonal(nearbyCarPort);
        Packet packet = new Packet();
        packet.setData(gson.toJson(response));
        packet.setCommand(PAGE_PERSONAL_RESPONSE);
        return packet;
    }

    private Packet handlerPageRequest(PageRequest pageRequest) throws ParseException {
        List<ParkingLot> nearbyParkingLot = bookSearchService.findNearbyParkingLot(pageRequest);
        PageResponse response = new PageResponse();
        response.setPage(nearbyParkingLot);
        Packet packet = new Packet();
        packet.setData(gson.toJson(response));
        packet.setCommand(PAGE_RESPONSE);
        return packet;
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendList(Integer userId,PageRequest pageRequest) throws ParseException, IOException {
        WebSocketServer webSocketServer = webSocketMap.get(userId);
        Packet packet = handlerPageRequest(pageRequest);
        webSocketServer.sendMessage(gson.toJson(packet));
    }

    public void sendPersonList(Integer userId,PagePersonalRequest pageRequest) throws ParseException, IOException {
        WebSocketServer webSocketServer = webSocketMap.get(userId);
        Packet packet = handlerPagePersonalRequest(pageRequest);
        webSocketServer.sendMessage(gson.toJson(packet));
    }


    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            onlineCount.decrementAndGet();
        }
    }
}
