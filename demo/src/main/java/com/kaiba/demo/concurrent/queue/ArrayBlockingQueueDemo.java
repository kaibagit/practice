package com.kaiba.demo.concurrent.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {

    private static final Logger log = LoggerFactory.getLogger(ArrayBlockingQueueDemo.class);

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        new Thread(() -> {
            try {
                for(int i=1;i<1000;i++) {
                    log.info("put {}",i);
                    queue.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for(int i=1;i<1000;i++) {
                    int v = queue.take();
                    log.info("get {}",v);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
