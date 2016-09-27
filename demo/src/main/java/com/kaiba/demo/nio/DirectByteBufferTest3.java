package com.kaiba.demo.nio;

import sun.misc.Cleaner;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DirectByteBufferTest3 {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        ByteBuffer buffer= ByteBuffer.allocateDirect(1024);
        Field cleanerField = buffer.getClass().getDeclaredField("cleaner");
        cleanerField.setAccessible(true);
        Cleaner cleaner = (Cleaner) cleanerField.get(buffer);
        cleaner.clean();
    }
}
