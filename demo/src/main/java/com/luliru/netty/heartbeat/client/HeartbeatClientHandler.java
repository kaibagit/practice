package com.luliru.netty.heartbeat.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2016/7/7.
 */
public class HeartbeatClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatClientHandler.class);

    /**
     * 心跳尝试ping的最大次数
     */
    private static final int maxHeartbeatPingTimes = 3;

    private int pingTimes = 0;

//    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(
//            Unpooled.copiedBuffer("Heartbeat", CharsetUtil.UTF_8));  // 1、定义了心跳时，要发送的内容

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {  // 2、判断是否是 IdleStateEvent 事件，是则处理
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = null;
            if (event.state() == IdleState.READER_IDLE) {
                type = "read idle";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }
            if(pingTimes < maxHeartbeatPingTimes){
                log.info( ctx.channel().remoteAddress()+"超时类型：" + type);

//            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(
//                    ChannelFutureListener.CLOSE_ON_FAILURE);  // 3、将心跳内容发送给服务端
                ctx.writeAndFlush("Heartbeat\n");
                pingTimes += 1;
            }else{
                ctx.close();
            }
        }else if("Heartbeat Reply".equals(evt)){
            log.info("Heartbeat Reply");
            pingTimes = 0;
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

}
