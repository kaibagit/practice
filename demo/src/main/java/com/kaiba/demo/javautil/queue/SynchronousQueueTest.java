package com.kaiba.demo.javautil.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by luliru on 2017/5/28.
 */
public class SynchronousQueueTest {

    private static final Logger log = LoggerFactory.getLogger(SynchronousQueueTest.class);

    public static void main(String[] args){
        SynchronousQueue queue = new SynchronousQueue();
        Thread producter = new Thread(){
            public void run(){
                try {
                    Thread.sleep(10000);
                    log.info("put begin");
                    queue.put("1");
                    log.info("put end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread customer = new Thread(){
            public void run(){
                try {
                    log.info("take begin");
                    queue.take();
                    log.info("take end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        producter.start();
        customer.start();

    }

}
