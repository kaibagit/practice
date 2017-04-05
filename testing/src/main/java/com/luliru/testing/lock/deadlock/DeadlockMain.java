package com.luliru.testing.lock.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2017/4/5.
 */
public class DeadlockMain {

    private static final Logger log = LoggerFactory.getLogger(DeadlockMain.class);

    public static void main(String[] args){
        final Object resourceA = new Object();
        final Object resourceB = new Object();
        Thread threadA = new Thread(){
            public void run(){
                log.info("begin do A.");
                synchronized (resourceA){
                    doSomething(3000L);
                    log.info("A done.");
                    log.info("begin do B.");
                    synchronized (resourceB){
                        doSomething(3000L);
                        log.info("B done.");
                    }
                }
            }
        };
        Thread threadB = new Thread(){
            public void run(){
                log.info("begin do B.");
                synchronized (resourceB){
                    doSomething(3000L);
                    log.info("B done.");
                    log.info("begin do A.");
                    synchronized (resourceA){
                        doSomething(3000L);
                        log.info("A done.");
                    }
                }
            }
        };

        threadA.start();
        threadB.start();
    }

    private static void doSomething(long millisecond){
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
