package com.kaiba.demo.net.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by luliru on 2016/12/10.
 */
public class TcpClient {

    private static final Logger log = LoggerFactory.getLogger(TcpClient.class);

    public static void main(String[] args) throws IOException {
        int threadNum = 20;
        final CyclicBarrier cb = new CyclicBarrier(threadNum, new Runnable() {
            //当所有线程到达barrier时执行
            @Override
            public void run() {
                log.info("start....");

            }
        });
        for(int i=0;i<threadNum;i++){
            final int finalI = i;
            new Thread(){
                public void run(){
                    try{
                        cb.await();
                        Socket socket = new Socket("127.0.0.1",8888);
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        out.writeUTF("hello,"+ finalI);
                        log.info("服务器："+in.readUTF());
                        in.close();
                        socket.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }.start();
        }

    }
}
