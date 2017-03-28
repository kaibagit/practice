package com.kaiba.demo.concurrent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Random;

import sun.misc.Unsafe;

/**
 * Created by luliru on 2017/3/27.
 */
public class UnsafeTest {

    private int value = 2;

    public static void main(String[] args) throws Exception {
        showDontFreeMemory();
    }

    public static void test() throws Exception {
        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        Unsafe unsafe = unsafeConstructor.newInstance();
//        Unsafe unsafe = Unsafe.getUnsafe();

        long offset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("value"));
        System.out.println(offset);
    }

    /**
     * 直接操作内存
     * @throws Exception
     */
    public static void showBytes() throws Exception {
        Unsafe unsafe = getUnsafe();

        // Writing to a memory - MAX VALUE Byte
        byte value = Byte.MAX_VALUE;
        long bytes = 1;
        // Allocate given memory size
        long memoryAddress = unsafe.allocateMemory(bytes);
        // Write value to the allocated memory
        unsafe.putByte(memoryAddress, value);

        // Output the value written and the memory address
        System.out.println("[Byte] Writing " + value + " under the " + memoryAddress + " address.");

        byte readValue = unsafe.getByte(memoryAddress);

        // Output the value from
        System.out.println("[Byte] Reading " + readValue + " from the " + memoryAddress + " address.");

        // C style! Release the Kraken... Memory!! :)
        unsafe.freeMemory(memoryAddress);
    }

    private static Unsafe getUnsafe() throws Exception {
// Get the Unsafe object instance
        Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (sun.misc.Unsafe) field.get(null);
    }

    /**
     * 重新分配内存
     */
    public static void showDontFreeMemory() {
        for (int t = 0; t < 100; t++) {
            new Thread() {
                public void run() {
                    System.out.println("Thread " + Thread.currentThread().getName() + " start!");
                    for (int i = 0; i < 1000000; i++) {
                        try {
                            Unsafe unsafe = getUnsafe();

                            // Writing random Long to a memory
                            long value = new Random().nextLong();
                            long bytes = Long.SIZE;
                            // Allocate given memory size
                            long memoryAddress = unsafe.allocateMemory(bytes);
                            // Write value to the allocated memory
                            unsafe.putLong(memoryAddress, value);

                            // Read the value from the memory
                            long readValue = unsafe.getLong(memoryAddress);

                            // Always free the memory !!
                            // ... FIXME: deallocate the memory used

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Thread " + Thread.currentThread().getName() + " stop!");
                }
            }.start();
        }
    }
}
