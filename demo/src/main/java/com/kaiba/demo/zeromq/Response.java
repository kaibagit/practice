package com.kaiba.demo.zeromq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

import java.io.IOException;

/**
 * Created by luliru on 2017/11/14.
 */
public class Response {

    private static final Logger log = LoggerFactory.getLogger(Response.class);

    public static void main (String[] args) throws IOException, InterruptedException {
        ZMQ.Context context = ZMQ.context(1);  //这个表示创建用于一个I/O线程的context

        ZMQ.Socket socket = context.socket(ZMQ.REP);  //创建一个response类型的socket，他可以接收request发送过来的请求，其实可以将其简单的理解为服务端
        socket.bind ("tcp://*:5555");    //绑定端口
        for(int i=0;i<20;i++){
            Thread.sleep(1000L);
            byte[] requestData = socket.recv();  //获取request发送过来的数据
            String request = new String(requestData);
            log.info("receive : " + new String(request));
            String response = "hello,"+request;
            socket.send(response.getBytes());  //向request端发送数据  ，必须要要request端返回数据，没有返回就又recv，将会出错，这里可以理解为强制要求走完整个request/response流程
        }
        socket.close();  //先关闭socket
        context.term();  //关闭当前的上下文
    }
}
