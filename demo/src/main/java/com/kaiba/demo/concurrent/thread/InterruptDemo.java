package com.kaiba.demo.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2017/7/11.
 */
public class InterruptDemo {

    private static final Logger log = LoggerFactory.getLogger(InterruptDemo.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Worker());
        thread.start();

        Thread.sleep(10000L);

        if (thread.isAlive()) {
            thread.interrupt();
            thread.join();
        }
    }

    private static class Worker implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    log.info("Worker Doing...");
                    Thread.sleep(3000L);
                }
            } catch (InterruptedException t) {
                log.info("Worker sleep被中断");
            }
            log.info("Worker 结束");
        }
    }
}
