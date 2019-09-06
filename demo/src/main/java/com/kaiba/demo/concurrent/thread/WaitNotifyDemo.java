package com.kaiba.demo.concurrent.thread;

import com.kaiba.demo.concurrent.ConditionDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * WaitNotifyDemo
 * Created by luliru on 2019-09-06.
 */
public class WaitNotifyDemo {

    public static final Logger log = LoggerFactory.getLogger(ConditionDemo.class);

    public static void main(String[] args){
        Object lock = new Object();
        Queue queue = new LinkedBlockingDeque();
        Producer producer = new Producer(queue,lock);
        Consumer consumer = new Consumer(queue,lock);
        producer.start();
        consumer.start();
    }
}

class Producer extends Thread{

    private Queue queue;
    private Object lock;

    public Producer(Queue queue,Object lock){
        this.queue = queue;
        this.lock = lock;
    }

    public void run(){
        for(int i=1;i<=100;){
            synchronized (lock){
                int size = queue.size();
                if(size < 10){
                    queue.add(i);
                    WaitNotifyDemo.log.info("push "+i);
                    i++;
                    lock.notify();
                    try {
                        Thread.sleep(20L);  //sleep一定时间，等待Consumer启动
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Consumer extends Thread{

    private Queue queue;
    private Object lock;

    public Consumer(Queue queue,Object lock){
        this.queue = queue;
        this.lock = lock;
    }

    public void run(){
        for(int i=1;i<=100;){
            synchronized (lock){
                Object obj = queue.poll();
                if(obj == null){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    WaitNotifyDemo.log.info("  poll "+obj);
                    i++;
                    lock.notify();
                    try {
                        Thread.sleep(20L);  //sleep一定时间，等待Producer启动
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
