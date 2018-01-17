package com.kaiba.demo.concurrent.lock.reentrant;

/**
 * Created by luliru on 2018/1/17.
 */
public class NotUseReentrantLock {

    public static void main(String[] args) {
        Object lock = new Object();

        Thread writer = new Thread(){
            public void run(){
                synchronized (lock) {
                    long startTime = System.currentTimeMillis();
                    System.out.println("开始往这个buff写入数据…");
                    for (;;)// 模拟要处理很长时间
                    {
                        if (System.currentTimeMillis()
                                - startTime > Integer.MAX_VALUE)
                            break;
                    }
                    System.out.println("终于写完了");
                }
            }
        };

        Thread reader = new Thread(){
            public void run(){
                synchronized (lock) {
                    System.out.println("从这个buff读数据");
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
                    //等5秒钟去中断读
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

