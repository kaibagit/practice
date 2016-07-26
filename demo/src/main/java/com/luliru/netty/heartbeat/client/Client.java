package com.luliru.netty.heartbeat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by luliru on 2016/7/7.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            // 发起异步连接操作
            ChannelFuture f = b.connect("localhost", 8000).sync();
            // 当代客户端链路关闭
            f.channel().closeFuture().sync();

            while(true){
                Thread.sleep(100L);
            }
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }

}
