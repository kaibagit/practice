package com.kaiba.demo.zeromq;

import org.zeromq.ZMQ;

import java.io.IOException;

/**
 * Created by luliru on 2017/11/14.
 */
public class Publisher {

    public static void main(String args[]) throws IOException, InterruptedException {
        ZMQ.Context context = ZMQ.context(1);  //创创建包含一个I/O线程的context
        ZMQ.Socket publisher = context.socket(ZMQ.PUB);   //创建一个publisher类型的socket，他可以向所有订阅的subscriber广播数据

        publisher.bind("tcp://*:5555");  //将当前publisher绑定到5555端口上，可以接受subscriber的订阅

        for(int i=0;i<100;i++){
            String message = "fjs hello";  //最开始可以理解为pub的channel，subscribe需要订阅fjs这个channel才能接收到消息
            publisher.send(message.getBytes());
            Thread.sleep(1000L);
        }

        publisher.close();
        context.term();
    }
}
