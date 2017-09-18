package com.serialize;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectInput;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2SerializerFactory;
import sun.misc.Unsafe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by luliru on 2017/9/15.
 */
public class CustomizedObjectInput implements ObjectInput {

    private Hessian2ObjectInput hessian2ObjectInput;

    public CustomizedObjectInput(InputStream is)
    {
        hessian2ObjectInput = new Hessian2ObjectInput(is);
    }

    @Override
    public Object readObject() throws IOException, ClassNotFoundException {
        Object obj = hessian2ObjectInput.readObject();
        if("_____Throwable".equals(obj)){
            String clazz = hessian2ObjectInput.readUTF();
            String message = hessian2ObjectInput.readUTF();
            try {
                Throwable t = (Exception) getUnsafe().allocateInstance(Class.forName(clazz));
                Field field = Exception.class.getField("detailMessage");
                field.setAccessible(true);
                field.set(t,message);
                return t;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    @Override
    public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public <T> T readObject(Class<T> cls, Type type) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean readBool() throws IOException {
        return hessian2ObjectInput.readBool();
    }

    @Override
    public byte readByte() throws IOException {
        return hessian2ObjectInput.readByte();
    }

    @Override
    public short readShort() throws IOException {
        return hessian2ObjectInput.readShort();
    }

    @Override
    public int readInt() throws IOException {
        return hessian2ObjectInput.readInt();
    }

    @Override
    public long readLong() throws IOException {
        return hessian2ObjectInput.readLong();
    }

    @Override
    public float readFloat() throws IOException {
        return hessian2ObjectInput.readFloat();
    }

    @Override
    public double readDouble() throws IOException {
        return hessian2ObjectInput.readDouble();
    }

    @Override
    public String readUTF() throws IOException {
        return hessian2ObjectInput.readUTF();
    }

    @Override
    public byte[] readBytes() throws IOException {
        return hessian2ObjectInput.readBytes();
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
}
