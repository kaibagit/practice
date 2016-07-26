package com.luliru.netty.heartbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2016/7/11.
 */
public class HeartbeatPongHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatPongHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if("ping".equals(msg)){
            log.info("receive ping");
            ctx.writeAndFlush("pong\n");
        }else{
            ctx.fireChannelRead(msg);
        }
    }
}
