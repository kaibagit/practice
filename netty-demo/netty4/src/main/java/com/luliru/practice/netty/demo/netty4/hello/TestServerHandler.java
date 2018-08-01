package com.luliru.practice.netty.demo.netty4.hello;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by luliru on 2016/11/19.
 */
public class TestServerHandler extends SimpleChannelInboundHandler<String> {

    public TestServerHandler(){
        System.out.println(this + " init.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("channel => "+ctx.channel());
        System.out.println("ChannelHandler => "+this);
        System.out.println("thread => "+ctx.executor());
        Thread.sleep(10000);
        ctx.fireChannelRead(msg);
    }
}
