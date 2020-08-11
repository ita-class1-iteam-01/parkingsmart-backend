package com.oocl.parkingsmart.websocket.server;

import com.oocl.parkingsmart.websocket.initializer.WebSocketChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class NettyServer{
    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;
    private WebSocketChannelInitializer initializer;
    public NettyServer(WebSocketChannelInitializer initializer) {
        this.initializer = initializer;
    }

    public ChannelFuture bing(InetSocketAddress address){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        log.info("[Netty server] server start");
                    }
                })
                //指定TCP队列大小
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(initializer);
        ChannelFuture channelFuture = serverBootstrap.bind(address).syncUninterruptibly();
        channel = channelFuture.channel();
//        {"version":1,"command":0,"data":"str"}
        String str = "{\"latitude\":\"str\",\"longitude\":\"str\",\"startTime\":\"str\",\"endTime\":\"str\"}";
        return channelFuture;
    }

    public void destroy(){
        log.info("[Netty server]server end");
        if(channel==null){
            return;
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
