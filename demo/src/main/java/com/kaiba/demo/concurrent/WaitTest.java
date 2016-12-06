package com.kaiba.demo.concurrent;

/**
 * Created by luliru on 2016/11/28.
 */
public class WaitTest {

    public static void main(String[] args){
        final Object lock = new Object();
        final Thread mainThread = Thread.currentThread();
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开始唤醒");
                synchronized (lock){
                    //lock.notifyAll();
                    mainThread.interrupt();
                }
            }
        }.start();
        synchronized (lock){
            try {
                lock.wait(10000);
                System.out.println("唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("finished");
    }
}
