package com.kaiba.demo.nio.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by luliru on 2017/2/3.
 */
public class Acceptor implements Handler{

    @Override
    public void accept(ChannelContext ctx) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)ctx.getChannel();
        Selector selector = ctx.getSelector();
        SocketChannel socketChannel = serverSocketChannel.accept(); // 建立连接
        socketChannel.configureBlocking(false);

        SelectionKey readKey = socketChannel.register(selector, SelectionKey.OP_READ); // 注册Socket通道的Read事件
        ChannelContext readCtx = new ChannelContext(readKey);
        readKey.attach(readCtx);
        System.out.println("Connected: " + socketChannel.socket().getRemoteSocketAddress());
    }

    @Override
    public void read(ChannelContext ctx) throws IOException {
        throw new RuntimeException("not support read");
    }

    @Override
    public void write(ChannelContext ctx) {
        throw new RuntimeException("not support write");
    }
}
