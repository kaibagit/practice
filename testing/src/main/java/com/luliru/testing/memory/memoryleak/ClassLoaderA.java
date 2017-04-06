package com.luliru.testing.memory.memoryleak;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by luliru on 2017/4/6.
 */
public class ClassLoaderA extends ClassLoader {

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    protected Class<?> loadClass(String name, boolean resolve)throws ClassNotFoundException{
        Class<?> c = null;
        if(name.startsWith("java.")){
            c = this.getParent().loadClass(name);
        }else{
            c = findLoadedClass(name);
            if(c == null){
                c = findClass(name);
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

    @Override
    public Class<?> findClass(String name){
        byte[] classData = null;
        try{
            FileInputStream f = new FileInputStream("C:\\Users\\Administrator\\workspace\\practice\\testing\\target\\classes\\"+name.replace(".","/")+".class");
            int num = f.available();
            classData = new byte[num];
            f.read(classData);
        }catch(IOException e){
            e.printStackTrace();
        }
        Class<?> x = defineClass(name, classData, 0, classData.length);
        return x;
    }

}
