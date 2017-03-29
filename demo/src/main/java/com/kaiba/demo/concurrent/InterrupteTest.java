package com.kaiba.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2017/3/29.
 */
public class InterrupteTest {

    private final static Logger logger = LoggerFactory.getLogger(InterrupteTest.class);

    public static void main(String[] args) throws InterruptedException {
        case_5();
    }

    /**
     * 先wait，再interrupt中断
     * @throws InterruptedException
     */
    public static void case_one() throws InterruptedException {
        final Object lock = new Object();
        Thread thread = new Thread(){

            public void run(){
                synchronized (lock){
                    try {
                        System.out.println("Sub thread begin wait");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        Thread.sleep(1000L);    //等待子线程wait
        thread.interrupt();
        System.out.println("Main thread over");
    }

    /**
     * 先interrupt()，再wait()，线程直接抛出InterruptedException
     */
    public static void case_two(){
        final Object lock = new Object();
        Thread thread = new Thread(){

            public void run(){
                synchronized (lock){
                    try {
                        this.interrupt();
                        System.out.println("Sub thread begin wait");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        System.out.println("Main thread over");
    }

    public static void case_three(){
        Thread thread = new Thread(){

            public void run(){
                try {
                    this.interrupt();
                    System.out.println("Sub thread begin sleep");
                    this.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        System.out.println("Main thread over");
    }

    public static void case_4() throws InterruptedException {
        Thread thread = new Thread(){

            public void run(){
                try {
                    System.out.println("Sub thread begin sleep");
                    this.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        Thread.sleep(1000L);    //等待子线程wait
        thread.interrupt();
        System.out.println("Main thread over");
    }

    public static void case_5() throws InterruptedException {
        final Object monitor = new Object();
        Thread thread = new Thread(){
            public void run(){
                synchronized(monitor){
                    while(!this.isInterrupted()){
                        System.out.println("Sub thread do something...");
                        System.out.println("Sub thread wait for shutdown.");
                        try {
                            monitor.wait(); //notify 不会抛出异常;interrupt()抛出异常
                            System.out.println("Sub thread wake up");
                            System.out.println("----"+this.isInterrupted());
                            this.interrupt();
                        } catch (InterruptedException e) {
                            System.out.println("----"+this.isInterrupted());
                            this.interrupt();
                        }
                    }
                    System.out.println("Sub thread shutdown.");
                }
            }
        };
        thread.start();
        System.out.println("Main thread do something...");
        Thread.sleep(1000L);
        System.out.println("Main thread begin shutdown sub thread.");
        synchronized (monitor){
            System.out.println("==="+thread.isInterrupted());
//            monitor.notify();
            thread.interrupt();
            System.out.println("==="+thread.isInterrupted());
            System.out.println("==="+thread.isInterrupted());
        }
        System.out.println("Main thread over.");
    }
}
