package com.luliru.practice.netty.demo.netty4;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luliru on 2017/4/27.
 */
public class HashedWheelTimerTest {

    public static void main(String[] args) throws InterruptedException {
        test_3();
    }

    public static void test_1() throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task :" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
    }

    /**
     * 当前一个任务执行时间过长的时候，会影响后续任务的到期执行时间的。也就是说其中的任务是串行执行的。所以，要求里面的任务都要短平快。
     * @throws InterruptedException
     */
    public static void test_2() throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("task1:" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(
                formatter)), 4, TimeUnit.SECONDS);
    }

    /**
     * 即使配置了ThreadFactory，结果只从ThreadFactory中取一个线程
     * @throws InterruptedException
     */
    public static void test_3() throws InterruptedException {
        ThreadFactory threadFactory = new SimpleThreadFactory();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(threadFactory,100, TimeUnit.MILLISECONDS);
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("Thread:"+Thread.currentThread()+" task1:" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(timeout -> System.out.println("Thread:"+Thread.currentThread()+" task2:" + LocalDateTime.now().format(
                formatter)), 4, TimeUnit.SECONDS);
    }
}

class SimpleThreadFactory implements ThreadFactory {

    private AtomicInteger tid = new AtomicInteger(1);

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("SimpleThreadFactory-"+tid.getAndIncrement());
        return thread;
    }
}
