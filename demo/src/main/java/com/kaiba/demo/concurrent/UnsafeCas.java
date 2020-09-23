package com.kaiba.demo.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeCas {

    private static final long valueOffset;

    private static final Unsafe unsafe;

    static {
        try {
            Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
            unsafeConstructor.setAccessible(true);
            unsafe = unsafeConstructor.newInstance();
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private int value = 0;

    public static void main(String[] args) throws InterruptedException {
        UnsafeCas obj = new UnsafeCas();
        unsafe.getAndAddInt(obj, valueOffset, 1);
        CountDownLatch latch = new CountDownLatch(3);
        new Thread( ()-> {
            for(int i=0;i<1000000;i++) {
                unsafe.getAndAddInt(obj, valueOffset, 1);
            }
            latch.countDown();
        }).start();

        new Thread( ()-> {
            for(int i=0;i<1000000;i++) {
                unsafe.getAndAddInt(obj, valueOffset, 1);
            }
            latch.countDown();
        }).start();

        new Thread( ()-> {
            for(int i=0;i<1000000;i++) {
                unsafe.getAndAddInt(obj, valueOffset, 1);
            }
            latch.countDown();
        }).start();

        latch.await();

        System.out.println(obj.value);
    }
}
