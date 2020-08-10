package com.oocl.parkingsmart.websocket.handler;

import com.oocl.parkingsmart.websocket.protocol.data.PageRequest;
import com.oocl.parkingsmart.websocket.protocol.data.PageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;

public class PageRequestHandler extends SimpleChannelInboundHandler<PageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PageRequest pageRequest) throws Exception {
        //拿到数据
        String startTime = pageRequest.getStartTime();
        String endTime = pageRequest.getEndTime();
        String latitude = pageRequest.getLatitude();
        String longitude = pageRequest.getLongitude();
        //todo call method to get data
        Page<String> page = new PageImpl<>(Arrays.asList("test1","test2"));
        PageResponse response = new PageResponse();
        response.setPage(page);
        ctx.channel().writeAndFlush(response);
    }
}
