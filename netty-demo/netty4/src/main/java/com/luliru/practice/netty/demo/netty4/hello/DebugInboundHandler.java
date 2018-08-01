package com.luliru.practice.netty.demo.netty4.hello;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2016/7/11.
 */
public class DebugInboundHandler implements ChannelInboundHandler {

    private static final Logger log = LoggerFactory.getLogger(DebugInboundHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" channelRegistered "+this);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" channelUnregistered "+this);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" channelActive "+this);
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" channelInactive "+this);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" channelRead "+this);
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" channelReadComplete "+this);
        ctx.fireChannelReadComplete();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" userEventTriggered "+this);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" channelWritabilityChanged "+this);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" handlerAdded "+this);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" handlerRemoved "+this);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(">>>>>>>>>>"+ctx.toString()+" exceptionCaught "+this);
    }

}
