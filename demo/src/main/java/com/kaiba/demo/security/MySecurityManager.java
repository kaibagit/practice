package com.kaiba.demo.security;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by luliru on 2018/8/21.
 */
public class MySecurityManager extends SecurityManager {

    @Override
    public void checkRead(String file) {
        //super.checkRead(file, context);
        if (file.endsWith("test"))
            throw new SecurityException("你没有读取的本文件的权限");
    }

    public static void main(String[] args) throws IOException {
        System.setSecurityManager(new MySecurityManager());
        FileInputStream fis = new FileInputStream("test");
        System.out.println(fis.read());
    }
}
