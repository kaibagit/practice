package com.kaiba.demo.zeromq;

import org.zeromq.ZMQ;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luliru on 2017/11/14.
 */
public class Pull {

    public static void main(String args[]) {
        final AtomicInteger number = new AtomicInteger(0);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable(){
                private int here = 0;
                public void run() {
                    // TODO Auto-generated method stub
                    ZMQ.Context context = ZMQ.context(1);
                    ZMQ.Socket pull = context.socket(ZMQ.PULL);
                    pull.connect("ipc://fjs");
                    //pull.connect("ipc://fjs");
                    while (true) {
                        String message = new String(pull.recv());
                        int now = number.incrementAndGet();
                        here++;
                        if (now % 1000000 == 0) {
                            System.out.println(now + "  here is : " + here);
                        }
                    }
                }

            }).start();

        }
    }
}
