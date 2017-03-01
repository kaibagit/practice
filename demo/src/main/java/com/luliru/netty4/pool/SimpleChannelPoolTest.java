package com.luliru.netty4.pool;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by kaiba on 2017/3/1.
 */
public class SimpleChannelPoolTest {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.remoteAddress("localhost",8080);
        bootstrap.group(group)
           .channel(NioSocketChannel.class);

        CountingChannelPoolHandler poolHandler = new CountingChannelPoolHandler();

        ChannelPool pool = new SimpleChannelPool(bootstrap, poolHandler);

        Channel channel = pool.acquire().sync().getNow();
        Channel channel2 = pool.acquire().sync().getNow();
        pool.release(channel).syncUninterruptibly();
        pool.release(channel2).syncUninterruptibly();

        group.shutdownGracefully();
    }
}
