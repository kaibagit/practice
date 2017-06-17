package com.kaiba.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;

/**
 * Created by luliru on 2017/3/13.
 */
public class DelayedQueueDemo {

    private static final Logger log = LoggerFactory.getLogger(DelayedQueueDemo.class);

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();
        DelayedElement el1 = new DelayedElement(5000);
        DelayedElement el2 = new DelayedElement(3000);
        delayQueue.offer(el1);
        log.info("offer {}",el1);
        delayQueue.offer(el2);
        log.info("offer {}",el2);
        while (true){
            DelayedElement element = delayQueue.take();
            log.info("tak {}",element);
        }
    }
}
