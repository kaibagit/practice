package com.luliru.practice.netty.demo.netty4.heartbeat2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyTest {

    private static final Logger log = LoggerFactory.getLogger(NettyTest.class);

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        private static final int IDEL_TIME_OUT = 10;
                        private static final int READ_IDEL_TIME_OUT = 4;
                        private static final int WRITE_IDEL_TIME_OUT = 5;

                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("idleStateHandler", new IdleStateHandler(READ_IDEL_TIME_OUT, WRITE_IDEL_TIME_OUT, IDEL_TIME_OUT));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void userEventTriggered(
                                        ChannelHandlerContext ctx, Object evt)
                                        throws Exception {
                                    if(IdleStateEvent.class.isAssignableFrom(evt.getClass())){
                                        IdleStateEvent event = (IdleStateEvent) evt;
                                        if(event.state() == IdleState.READER_IDLE)
                                            log.info("read idle");
                                        else if(event.state() == IdleState.WRITER_IDLE)
                                            log.info("write idle");
                                        else if(event.state() == IdleState.ALL_IDLE)
                                            log.info("all idle");
                                    }
                                }
                            });
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
