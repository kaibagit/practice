package com.kaiba.demo.zeromq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

/**
 * Created by luliru on 2017/11/14.
 */
public class Pull {

    private static final Logger log = LoggerFactory.getLogger(Pull.class);

    public static void main(String args[]) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                ZMQ.Context context = ZMQ.context(1);
                ZMQ.Socket pull = context.socket(ZMQ.PULL);
                pull.connect("ipc://fjs");
                while (true) {
                    String message = new String(pull.recv());
                    log.info("receive : " + message);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
