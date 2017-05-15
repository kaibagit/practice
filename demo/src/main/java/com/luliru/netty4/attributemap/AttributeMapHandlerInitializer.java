package com.luliru.netty4.attributemap;

import com.luliru.netty4.hello.DebugInboundHandler;
import com.luliru.netty4.hello.HelloServerHandler;
import com.luliru.netty4.hello.TestServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

/**
 * Created by luliru on 2016/7/11.
 */
public class AttributeMapHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("debug",new DebugInboundHandler());

        // 以("\n")为结尾分割的 解码器
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
                Delimiters.lineDelimiter()));

        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        pipeline.addLast(new DefaultEventExecutorGroup(2),new TestServerHandler());

        // 自己的逻辑Handler
        pipeline.addLast("handler", new HandlerOne());
    }
}

