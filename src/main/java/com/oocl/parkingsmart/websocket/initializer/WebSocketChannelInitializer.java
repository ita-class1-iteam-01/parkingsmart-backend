package com.oocl.parkingsmart.websocket.initializer;

import com.oocl.parkingsmart.websocket.codeC.PacketToDataCodeC;
import com.oocl.parkingsmart.websocket.codeC.WebSocketProtocolCodeC;
import com.oocl.parkingsmart.websocket.handler.PageRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    WebSocketProtocolCodeC webSocketProtocolCodeC;
    @Autowired
    PacketToDataCodeC packetToDataCodeC;
    @Autowired
    PageRequestHandler pageRequestHandler;
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new HttpServerCodec());
        ch.pipeline().addLast(new ChunkedWriteHandler());
        ch.pipeline().addLast(new HttpObjectAggregator(2048*64));
        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
        //CodeC
        ch.pipeline().addLast(webSocketProtocolCodeC);
        ch.pipeline().addLast(packetToDataCodeC);
        //handler
        ch.pipeline().addLast(pageRequestHandler);
    }
}
