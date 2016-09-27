package com.kaiba.demo.nio;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DirectByteBufferTest2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        System.out.println("maxMemoryValue:"+sun.misc.VM.maxDirectMemory());
        ByteBuffer buffer = ByteBuffer.allocateDirect(0);
        Class c = Class.forName("java.nio.Bits");
        Field maxMemory = c.getDeclaredField("maxMemory");
        maxMemory.setAccessible(true);
        synchronized (c) {
            Long maxMemoryValue = (Long)maxMemory.get(null);
            System.out.println("maxMemoryValue:"+maxMemoryValue);
        }
    }
}
