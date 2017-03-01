package com.luliru.netty4.client;

import com.luliru.netty4.hello.HelloClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaiba on 2017/3/1.
 */
public class MultiConnectionsClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .handler(new HelloClientInitializer());

        List<Channel> channelList = new ArrayList<>();
        List<Runnable> runnableList = new ArrayList<>();
        for(int i=0;i< 10;i++){
            final Channel ch = b.connect("localhost", 8080).sync().channel();
            channelList.add(ch);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ch.writeAndFlush("hello\r\n");
                }
            };
            runnableList.add(runnable);
        }

        for(Runnable runnable : runnableList){
            new Thread(runnable).start();
        }
    }
}
