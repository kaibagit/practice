package com.kaiba.demo.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by luliru on 2017/3/29.
 */
public class LockSupportTest {

    public static void main(String[] args){
        final Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this+" over.");
                LockSupport.unpark(mainThread); //唤醒Main线程
//                mainThread.interrupt();       //唤醒Main线程
            }
        };
        thread.start();
        LockSupport.park();     //休眠Main线程
        System.out.println("Main thread over");
    }
}
