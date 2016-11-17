package com.luliru.netty4.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by kaiba on 2016/5/12.
 */
public class EchoServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 自己的逻辑Handler
        pipeline.addLast("handler", new EchoServerHandler());
    }
}
