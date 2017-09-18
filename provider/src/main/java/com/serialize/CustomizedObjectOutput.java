package com.serialize;

import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectOutput;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2SerializerFactory;
import sun.misc.Unsafe;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * Created by luliru on 2017/9/15.
 */
public class CustomizedObjectOutput implements ObjectOutput {

    private Hessian2ObjectOutput hessian2ObjectOutput;

    private OutputStream os;

    public CustomizedObjectOutput(OutputStream os)
    {
        hessian2ObjectOutput = new Hessian2ObjectOutput(os);
        this.os = os;
    }

    @Override
    public void writeObject(Object obj) throws IOException {
        if(obj != null && obj instanceof Throwable){
            Throwable t = (Throwable) obj;
//            Unsafe
//            Throwable copy = (Throwable) shallowCopy(obj);
//            copy.setStackTrace(null);
//            hessian2ObjectOutput.writeObject(copy);
            hessian2ObjectOutput.writeUTF("_____Throwable");
            hessian2ObjectOutput.writeUTF(t.getClass().getName());
            hessian2ObjectOutput.writeUTF(t.getMessage());
        }else{
            hessian2ObjectOutput.writeObject(obj);
        }
    }

    @Override
    public void writeBool(boolean v) throws IOException {
        hessian2ObjectOutput.writeBool(v);
    }

    @Override
    public void writeByte(byte v) throws IOException {
        hessian2ObjectOutput.writeByte(v);
    }

    @Override
    public void writeShort(short v) throws IOException {
        hessian2ObjectOutput.writeShort(v);
    }

    @Override
    public void writeInt(int v) throws IOException {
        hessian2ObjectOutput.writeInt(v);
    }

    @Override
    public void writeLong(long v) throws IOException {
        hessian2ObjectOutput.writeLong(v);
    }

    @Override
    public void writeFloat(float v) throws IOException {
        hessian2ObjectOutput.writeFloat(v);
    }

    @Override
    public void writeDouble(double v) throws IOException {
        hessian2ObjectOutput.writeDouble(v);
    }

    @Override
    public void writeUTF(String v) throws IOException {
        hessian2ObjectOutput.writeUTF(v);
    }

    @Override
    public void writeBytes(byte[] v) throws IOException {
        hessian2ObjectOutput.writeBytes(v);
    }

    @Override
    public void writeBytes(byte[] v, int off, int len) throws IOException {
        hessian2ObjectOutput.writeBytes(v,off,len);
    }

    @Override
    public void flushBuffer() throws IOException {
        hessian2ObjectOutput.flushBuffer();
    }

    public static Unsafe getUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long sizeOf(Object o) {
        Unsafe u = getUnsafe();
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        return ((maxSize/8) + 1) * 8;   // padding
    }

    static long toAddress(Object obj) {
        Object[] array = new Object[] {obj};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        return normalize(getUnsafe().getInt(array, baseOffset));
    }

    static Object fromAddress(long address) {
        Object[] array = new Object[] {null};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }

    private static long normalize(int value) {
        if(value >= 0) return value;
        return (~0L >>> 32) & value;
    }

    static Object shallowCopy(Object obj) {
        long size = sizeOf(obj);
        long start = toAddress(obj);
        long address = getUnsafe().allocateMemory(size);
        getUnsafe().copyMemory(start, address, size);
        return fromAddress(address);
    }


}
