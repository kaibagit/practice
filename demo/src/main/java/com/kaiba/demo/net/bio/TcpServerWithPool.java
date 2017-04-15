package com.kaiba.demo.net.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2016/12/10.
 */
public class TcpServerWithPool {

    private static final Logger log = LoggerFactory.getLogger(TcpServerWithPool.class);

    public static void main(String[] args) throws IOException {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 1, TimeUnit.MINUTES, queue);

        ServerSocket server = new ServerSocket(8888);

        while(true){
            final Socket socket = server.accept();	//接受socket连接，在连接传入之前一直阻塞
            log.info("accept:"+socket.getPort());
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            };
            executor.execute(runnable);
            log.info(executor.getQueue().size()+"");
        }
    }

    private static void handler(Socket socket){
        try{
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            log.info("客户端："+in.readUTF());
            Thread.sleep(1000);
            out.writeUTF("你好，客户端地址信息："+socket.getInetAddress()+"\t客户端通信端口号："+socket.getPort());
            out.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
