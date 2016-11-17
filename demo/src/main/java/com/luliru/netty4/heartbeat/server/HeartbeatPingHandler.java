package com.luliru.netty4.heartbeat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2016/7/11.
 */
public class HeartbeatPingHandler extends ChannelInboundHandlerAdapter{

    private static final Logger log = LoggerFactory.getLogger(HeartbeatPingHandler.class);

    private static final int MAX_TRY_TIMES = 3;

    private int tryTimes = 0;

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if("pong".equals(msg)){
            log.info("receive pong");
            tryTimes = 0;
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.state() == IdleState.READER_IDLE){
                log.info("IdleState.READER_IDLE");
            }else if(event.state() == IdleState.WRITER_IDLE){
                log.info("IdleState.WRITER_IDLE");
            }else if(event.state() == IdleState.ALL_IDLE){
                log.info("IdleState.ALL_IDLE");
                if(tryTimes < MAX_TRY_TIMES){
                    tryTimes = tryTimes + 1;
                    ctx.writeAndFlush("ping\n");
                }else{
                    ctx.close();
                }
            }
        }else{
            ctx.fireUserEventTriggered(evt);
        }
    }
}
