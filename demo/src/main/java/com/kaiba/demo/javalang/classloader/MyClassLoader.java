package com.kaiba.demo.javalang.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by luliru on 2018/8/20.
 */
public class MyClassLoader extends ClassLoader {

    private static final Logger log = LoggerFactory.getLogger(MyClassLoader.class);

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = getByteArray(name);
        if (data == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, data, 0, data.length);
    }


    private byte[] getByteArray(String name){
        String filePath =   name.replace(".", File.separator);
        byte[] buf = null;
        try {
            FileInputStream in = new FileInputStream(filePath);
            buf = new byte[in.available()];
            in.read(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf;
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        log.info("{}",MyClassLoader.class.getClassLoader());
        log.info("{}",myClassLoader.getParent());

        //用自定义的类装载器来装载类,这是动态扩展的一种途径
        Class cls2 = myClassLoader.loadClass("com.kaiba.demo.javalang.classloader.ToBeLoadBean",true);
        cls2.newInstance();
        log.info("{}",cls2.getClassLoader());
    }
}
