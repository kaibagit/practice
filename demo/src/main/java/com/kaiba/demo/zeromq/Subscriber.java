package com.kaiba.demo.zeromq;

import org.zeromq.ZMQ;

/**
 * Created by luliru on 2017/11/14.
 */
public class Subscriber {

    public static void main(String args[]) {
        ZMQ.Context context = ZMQ.context(1);  //创建1个I/O线程的上下文
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);     //创建一个sub类型，也就是subscriber类型的socket
        subscriber.connect("tcp://127.0.0.1:5555");    //与在5555端口监听的publisher建立连接
        subscriber.subscribe("fjs".getBytes());     //订阅fjs这个channel

        byte[] message = subscriber.recv();  //接收publisher发送过来的消息
        System.out.println("receive : " + new String(message));

        subscriber.close();
        context.term();
    }
}
