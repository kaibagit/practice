package com.kaiba.demo.concurrent.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;

/**
 * Created by luliru on 2017/3/13.
 */
public class DelayQueueDemo {

    private static final Logger log = LoggerFactory.getLogger(DelayQueueDemo.class);

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayElement> delayQueue = new DelayQueue<>();
        DelayElement el1 = new DelayElement(5000);
        DelayElement el2 = new DelayElement(3000);
        delayQueue.offer(el1);
        log.info("offer {}",el1);
        delayQueue.offer(el2);
        log.info("offer {}",el2);
        while (true){
            DelayElement element = delayQueue.take();
            log.info("tak {}",element);
        }
    }
}
