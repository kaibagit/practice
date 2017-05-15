package com.luliru.netty4.attributemap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetAddress;

/**
 * Created by kaiba on 2017/5/15.
 */
public class HandlerOne extends SimpleChannelInboundHandler<String> {

    public static final AttributeKey<Object> MY_OPTIONS_KEY = AttributeKey.valueOf("my_options");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

        Attribute<Object> attr = ctx.attr(MY_OPTIONS_KEY);
        Object myOption = attr.get();
        if(myOption == null){
            myOption = new Object();
            attr.setIfAbsent(myOption);
            System.out.println("my_options 不存在，创建之: "+myOption);
        }else{
            System.out.println("my_options 已存在: "+myOption);
        }

        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message !\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress()
                + " active !");

        ctx.writeAndFlush("Welcome to "
                + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
