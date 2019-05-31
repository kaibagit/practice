package com.kaiba.demo.concurrent.jctools;

import org.jctools.maps.ConcurrentAutoTable;

import java.util.concurrent.CountDownLatch;

/**
 * ConcurrentAutoTable:替代AtomicLong，专门为高性能的counter设计的。
 * Created by luliru on 2019-05-31.
 */
public class ConcurrentAutoTableTest {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentAutoTable table = new ConcurrentAutoTable();
        UnsafeInt unsafeInt = new UnsafeInt();
        int threadCount = 100;
        int timesPerThread = 100000;
        CountDownLatch latch = new CountDownLatch(threadCount);
        CountDownLatch finishLatch = new CountDownLatch(threadCount);
        for(int i=0;i<threadCount;i++){
            new Thread(()->{
                latch.countDown();
                for(int j=0;j<timesPerThread;j++){
                    table.increment();
                    unsafeInt.value++;
                }
                finishLatch.countDown();
            }).start();
        }
        latch.await();
        finishLatch.await();
        System.out.println(table.get());
        System.out.println(unsafeInt.value);
    }

    static class UnsafeInt{
        int value = 0;
    }
}
