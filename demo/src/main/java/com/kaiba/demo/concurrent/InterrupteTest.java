package com.kaiba.demo.concurrent;

/**
 * Created by luliru on 2017/3/29.
 */
public class InterrupteTest {

    public static void main(String[] args) throws InterruptedException {
        case_one();
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
}
