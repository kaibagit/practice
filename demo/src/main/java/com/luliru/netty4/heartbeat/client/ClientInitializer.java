package com.luliru.netty4.heartbeat.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Created by luliru on 2016/7/7.
 */
public class ClientInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 以("\n")为结尾分割的 解码器
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 解码转String
        pipeline.addLast(new StringDecoder(Charset.forName("utf-8")));
        // 编码器 String
        pipeline.addLast(new StringEncoder(Charset.forName("utf-8")));
        pipeline.addLast(new HeartbeatPongHandler());
        pipeline.addLast("handler",new ClientHandler());
    }
}
