package com.luliru.netty4.udp.unicast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Created by luliru on 2019/2/20.
 */
public final class TheMomentServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new TheMomentServerHandler());

            b.bind(7686).sync().channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }
    }
}