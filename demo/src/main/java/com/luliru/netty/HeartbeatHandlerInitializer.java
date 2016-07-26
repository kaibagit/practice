package com.luliru.netty;

import com.luliru.netty.heartbeat.client.ClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2016/7/7.
 */
public class HeartbeatHandlerInitializer extends ChannelInitializer<Channel> {

    private static final int READ_IDEL_TIME_OUT = 4; // 读超时
    private static final int WRITE_IDEL_TIME_OUT = 5;// 写超时
    private static final int ALL_IDEL_TIME_OUT = 7; // 所有超时

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(READ_IDEL_TIME_OUT,
                WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT, TimeUnit.SECONDS)); // 1、使用了 IdleStateHandler ，分别设置了读、写超时的时间
        pipeline.addLast(new HeartbeatServerHandler()); // 2、定义了一个 HeartbeatPingHandler 处理器，用来处理超时时，发送心跳
        pipeline.addLast("handler",new ClientHandler());
    }

}
