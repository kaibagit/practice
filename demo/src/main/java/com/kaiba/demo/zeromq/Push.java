package com.kaiba.demo.zeromq;

import org.zeromq.ZMQ;

/**
 * Created by luliru on 2017/11/14.
 */
public class Push {

    public static void main(String args[]) {

        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket push  = context.socket(ZMQ.PUSH);
        push.bind("ipc://fjs");

        for (int i = 0; i < 10000000; i++) {
            push.send("hello".getBytes());
        }
        push.close();
        context.term();

    }
}
