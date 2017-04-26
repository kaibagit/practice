package com.kaiba.demo.nio.reactor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by luliru on 2017/4/17.
 */
public class FakeNetty {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel acceptorSvr = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 10000);
        acceptorSvr.socket().bind(address);
        acceptorSvr.configureBlocking(false);

        Selector selector = Selector.open();

//        SelectionKey key = acceptorSvr.register(selector,SelectionKey.OP_ACCEPT,ioHandler);

        while(true){
            if(selector.select() > 0){
                Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 获取发生的事件
                Iterator<SelectionKey> it = selectedKeys.iterator(); // 依次进行处理
                while (it.hasNext()) {

                }
            }
        }
    }
}
