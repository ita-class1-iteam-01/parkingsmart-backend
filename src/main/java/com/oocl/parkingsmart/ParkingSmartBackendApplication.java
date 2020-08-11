package com.oocl.parkingsmart;
import com.oocl.parkingsmart.websocket.server.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class ParkingSmartBackendApplication implements CommandLineRunner {
    @Value("${netty.host}")
    private String host;
    @Value("${netty.port}")
    private int port;
    @Autowired
    NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(ParkingSmartBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        InetSocketAddress address = new InetSocketAddress(host,port);
//        ChannelFuture future = nettyServer.bing(address);
//        Runtime.getRuntime().addShutdownHook(new Thread(()->nettyServer.destroy()));
//        //closeFuture()是开启了一个子线程server channel的监听器
//        //负责监听channel是否关闭的状态，syncUninterruptibly()让主线程同步等待子线程结果。
//        future.channel().closeFuture().syncUninterruptibly();
    }
}