package com.kaiba.demo.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * Created by luliru on 2017/3/13.
 */
public class DelayedQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        DelayedElement el1 = new DelayedElement(5000);
        DelayedElement el2 = new DelayedElement(3000);
        delayQueue.offer(el1);
        delayQueue.offer(el2);
        Delayed poll = null;
        while (poll == null) {
            poll = delayQueue.poll();
            System.out.println("poll result :" + poll);
            System.out.println("peek result :" + delayQueue.peek());
            Thread.sleep(500);
        }
    }
}
