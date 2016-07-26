package com.luliru.netty.hello;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2016/7/11.
 */
public class DebugHandler implements ChannelHandler {

    private static final Logger log = LoggerFactory.getLogger(DebugHandler.class);

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
