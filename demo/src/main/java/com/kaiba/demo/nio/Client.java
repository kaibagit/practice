package com.kaiba.demo.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luliru on 2017/2/8.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {
        SocketChannel socketChannel = SocketChannel.open(); // 创建SocketChannel
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 10000);
        socketChannel.socket().connect(address);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ByteBuffer buffer = ByteBuffer.allocate(1024); // 创建Buffer
        while (true) {
            buffer.clear();
            String time = sdf.format(new Date());
//            buffer.put(time.getBytes());
            buffer.put("a".getBytes());
            buffer.flip(); // 设置成读取状态
            socketChannel.write(buffer); // 发送数据
            Thread.sleep(1000);
        }
    }
}
