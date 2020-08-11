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
        //netty针对http编解码的处理类，但是这些只能处理像http get的请求,也就是数据带在url问号后面的http请求
        ch.pipeline().addLast(new HttpServerCodec());
        //大文件处理
        ch.pipeline().addLast(new ChunkedWriteHandler());
        //HttpMessage和HttpContent聚合成为一个FullHttpRquest或者FullHttpRsponse
        ch.pipeline().addLast(new HttpObjectAggregator(2048*64));
        // 处理除了TextWebSocketFrame以外的其他帧
        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
        //CodeC
        ch.pipeline().addLast(webSocketProtocolCodeC);
        ch.pipeline().addLast(packetToDataCodeC);
        //handler
        ch.pipeline().addLast(pageRequestHandler);
    }
}
