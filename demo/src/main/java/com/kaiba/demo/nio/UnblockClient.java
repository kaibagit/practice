package com.kaiba.demo.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by luliru on 2017/2/11.
 */
public class UnblockClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        //请求连接
        channel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 10000));

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ByteBuffer buffer = ByteBuffer.allocate(1024); // 创建Buffer

        while(true){
            if (selector.select() > 0) {
                Iterator ite = selector.selectedKeys().iterator();
                while(ite.hasNext()){
                    SelectionKey key = (SelectionKey) ite.next();

                    if(key.isConnectable()){
                        try{
                            while(channel.finishConnect()){
                                //只有当连接成功后才能注册OP_READ事件
                                key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                                System.out.println("Connected: " + channel.socket().getLocalAddress());
                            }
                        }catch (IOException e){
                            key.cancel();
                            throw e;
                        }
                    }
                    if(key.isWritable()){
                        buffer.clear();
                        String time = sdf.format(new Date());
                        buffer.put(time.getBytes());
                        buffer.flip(); // 设置成读取状态
                        channel.write(buffer); // 发送数据
                        Thread.sleep(1000);
                    }
                    if(key.isReadable()){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                        channel.read(byteBuffer);
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array()));
                    }
                    ite.remove();
                }
            }
        }
    }
}
