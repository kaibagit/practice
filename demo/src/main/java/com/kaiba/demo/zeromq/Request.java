package com.kaiba.demo.zeromq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

/**
 * Created by luliru on 2017/11/14.
 */
public class Request {

    private static final Logger log = LoggerFactory.getLogger(Request.class);

    public static void main(String args[]) {
        ZMQ.Context context = ZMQ.context(1);  //创建一个I/O线程的上下文
        ZMQ.Socket socket = context.socket(ZMQ.REQ);   //创建一个request类型的socket，这里可以将其简单的理解为客户端，用于向response端发送数据

        socket.connect("tcp://127.0.0.1:5555");   //与response端建立连接
        for (int i = 0; i < 20; i++) {
            socket.send(Integer.toString(i).getBytes());   //向reponse端发送数据
            log.info("send : " + i);
            byte[] response = socket.recv();   //接收response发送回来的数据  正在request/response模型中，send之后必须要recv之后才能继续send，这可能是为了保证整个request/response的流程走完
            log.info("receive : " + new String(response));
        }
    }
}
