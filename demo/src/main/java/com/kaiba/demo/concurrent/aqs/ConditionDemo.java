package com.kaiba.demo.concurrent.aqs;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 队列中最多存放10个元素，Producer放入元素之后，发出notEmpty信号;Consumer取出元素之后，发出notFull信号
 * Created by luliru on 2017/4/6.
 */
public class ConditionDemo {

    public static final Logger log = LoggerFactory.getLogger(ConditionDemo.class);

    public static void main(String[] args){
        Lock lock = new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();
        Queue queue = new LinkedBlockingDeque();
        Producer producer = new Producer(queue,lock,notFull,notEmpty);
        Consumer consumer = new Consumer(queue,lock,notFull,notEmpty);
        producer.start();
        consumer.start();
    }
}

class Producer extends Thread{

    private Queue queue;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    public Producer(Queue queue,Lock lock,Condition notFull,Condition notEmpty){
        this.queue = queue;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    public void run(){
        for(int i=1;i<=100;){
            lock.lock();
            try{
                int size = queue.size();
                if(size < 10){
                    queue.add(i);
                    ConditionDemo.log.info("push "+i);
                    i++;
                    notEmpty.signal();  //发出notEmpty信号
                    Thread.sleep(20L);  //sleep一定时间，等待Consumer启动
                }else{
                    notFull.await();    //等待notFull信号
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

class Consumer extends Thread{

    private Queue queue;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    public Consumer(Queue queue,Lock lock,Condition notFull,Condition notEmpty){
        this.queue = queue;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    public void run(){
        for(int i=1;i<=100;){
            lock.lock();
            try{
                Object obj = queue.poll();
                if(obj == null){
                    notEmpty.await();   //等待notEmpty信号
                }else{
                    ConditionDemo.log.info("  poll "+obj);
                    i++;
                    notFull.signal();   //发出notFull信号
                    Thread.sleep(20L);  //sleep一定时间，等待Producer启动
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
