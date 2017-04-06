package com.luliru.testing.memory.memoryleak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by luliru on 2017/4/6.
 */
public class ClassloaderCauseMemoryLeakDemo {

    private static final Logger log = LoggerFactory.getLogger(ClassloaderCauseMemoryLeakDemo.class);

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        ClassLoader classLoaderA = new ClassLoaderA();
        ClassLoader classLoaderB = new ClassLoaderB();
        Class<?> aClass = classLoaderA.loadClass("com.luliru.testing.memory.memoryleak.A");
        Class<?> aaClass = classLoaderA.loadClass("com.luliru.testing.memory.memoryleak.AA");
        Class<?> bClass = classLoaderB.loadClass("com.luliru.testing.memory.memoryleak.B");
        Object a = aClass.newInstance();
        Object b = bClass.newInstance();
        Method setObjMethod = bClass.getMethod("setObj",Object.class);
        setObjMethod.invoke(b,a);   //对象b hold对象a,a指向A.class，A.class指向ClassLoaderA，导致ClassLoaderA无法被回收

        //取消这些对象的引用，并gc
        a = null;
        aClass = null;
        classLoaderA = null;
        System.gc();

        Thread.sleep(Long.MAX_VALUE);
    }

}