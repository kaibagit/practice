package com.kaiba.demo.concurrent.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luliru on 2018/1/17.
 */
public class UseReentrantLock {

    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();

        Thread writer = new Thread(){
            public void run() {
                lock.lock();
                try {
                    long startTime = System.currentTimeMillis();
                    System.out.println("开始往这个buff写入数据…");
                    for (;;)// 模拟要处理很长时间
                    {
                        if (System.currentTimeMillis()
                                - startTime > Integer.MAX_VALUE)
                            break;
                    }
                    System.out.println("终于写完了");
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread reader = new Thread(){
            public void run() {
                try {
                    //尝试获取该锁，如果该锁已经被另一线程保持，则该线程休眠等待获取锁
                    //在等待获取锁的时候被中断，则抛出InterruptedException异常，并且清除当前线程的中断状态
                    lock.lockInterruptibly();
                    try {
                        System.out.println("从这个buff读数据");
                    } finally {
                        lock.unlock();
                    }
                } catch (InterruptedException e) {
                    System.out.println("我不读了");
                }

                System.out.println("读结束");
            }
        };

        writer.start();
        reader.start();

        new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                for (;;) {
                    if (System.currentTimeMillis()
                            - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        reader.interrupt();
                        break;
                    }

                }
            }
        }).start();
    }

}

