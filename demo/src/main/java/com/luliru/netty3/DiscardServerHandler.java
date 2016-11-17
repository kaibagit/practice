package com.luliru.netty3;

import org.jboss.netty.channel.*;

/**
 * Created by luliru on 2016/11/17.
 */
@ChannelPipelineCoverage("all")
public class DiscardServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();

        Channel ch = e.getChannel();
        ch.close();
    }
}
