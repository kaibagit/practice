package com.kaiba.demo.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by luliru on 2017/2/8.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open(); // 创建Selector

// 创建一个用于建立连接的ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 10000);
        serverSocketChannel.socket().bind(address);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册ServerSocket通道上的Accept事件

        while (true) {
            if (selector.select() > 0) { //这个方法会阻塞，直到至少有一个已注册的事件发生。当一个或者更多的事件发生时，select()方法将返回所发生的事件的数量。
                Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 获取发生的事件
                Iterator<SelectionKey> it = selectedKeys.iterator(); // 依次进行处理
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isConnectable()){
                        ((SocketChannel)key.channel()).finishConnect();
                    }
                    if (key.isAcceptable()) { // 如果是Accept事件
                        // 获取注册的ServerSocketChannel
                        serverSocketChannel = ((ServerSocketChannel) key.channel());
                        SocketChannel socketChannel = serverSocketChannel.accept(); // 建立连接
                        socketChannel.configureBlocking(false);
                        SelectionKey key_1 = socketChannel.register(selector, SelectionKey.OP_READ,ByteBuffer.allocate(1024)); // 注册Socket通道的Read事件
//                        System.out.println(key_1 + ":" +key_1.interestOps() );
//                        SelectionKey key_2 = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE); // 注册Socket通道的Read事件
//                        System.out.println(key_2 + ":" +key_2.interestOps() );
//                        System.out.println(key_1 + ":" +key_1.interestOps() );
                        System.out.println("Connected: " + socketChannel.socket().getRemoteSocketAddress());
                    }
                    if (key.isReadable()) { // 如果是Read事件
                        // 获取注册的SocketChannel
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        while (socketChannel.read(buffer) > 0) { // 读取接收到的数据
                            buffer.flip();
                            byte[] dst = new byte[buffer.limit()];
                            buffer.get(dst);
                            System.out.println(new String(dst));
                        }
                    }
                    it.remove(); // 需要将处理过的事件移除，如果我们没有删除处理过的键，那么它仍然会在主集合中以一个激活的键出现，这会导致我们尝试再次处理它。
                }
            }
        }
    }

    private void process(byte[] dataBytes){

    }
}
