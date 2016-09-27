package com.kaiba.demo.nio;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2016/9/12.
 */
public class DirectByteBufferTest {

    public static void main(String[] args) {
        int count = 100000;
        int cap = 1024 * 1024;
        testDirectBuf(count, cap);
        testNonDirectBuf(count, cap);

    }

    private static void testDirectBuf(int count, int cap) {
        long st;
        long ed;
        ByteBuffer byteBuf = null;
        st = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            byteBuf = ByteBuffer.allocateDirect(cap);

        }
        ed = System.currentTimeMillis();
        System.out.println("alloc directByteBuffer for " + count
                + " times spends " + (ed - st) + "ms");

        st = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            processBuf(byteBuf);
        }
        ed = System.currentTimeMillis();
        System.out.println("directByteBuffer process " + count
                + " times spends " + (ed - st) + "ms");
    }

    private static ByteBuffer testNonDirectBuf(int count, int cap) {
        long st = System.currentTimeMillis();
        ByteBuffer byteBuf = null;
        for (int i = 0; i < count; i++) {
            byteBuf = ByteBuffer.allocate(cap);
        }
        long ed = System.currentTimeMillis();
        System.out.println("alloc nonDirectByteBuffer for " + count
                + " times spends " + (ed - st) + "ms");
        st = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            processBuf(byteBuf);

        }
        ed = System.currentTimeMillis();
        System.out.println("nonDirectByteBuffer process " + count
                + " times spends " + (ed - st) + "ms");
        return byteBuf;
    }

    private static void processBuf(ByteBuffer buf) {
        byte[] bytes = "assfasf".getBytes();
        buf.put(bytes);
        for (int i = 0; i < bytes.length; i++) {
            byte b = buf.get(i);
            byte[] bytes2 = new byte[] { b };
            // System.out.print(new String(bytes2));
        }
        // System.out.println();
        // System.out.println(buf.capacity());
    }

}
