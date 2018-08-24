package com.kaiba.demo.security;

import sun.security.util.SecurityConstants;

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;

/**
 * Created by luliru on 2018/8/22.
 */
public class AccessControllerDemo {

    public static void main(String[] args){
        doPrivilegedAction();
    }

    public static void test(){
        Permission perOne = new FilePermission("/Users/luliru/IdeaProjects/practice/demo/src/main/resources/security/",SecurityConstants.FILE_READ_ACTION);
        Permission perAll = new FilePermission("/Users/luliru/IdeaProjects/*", SecurityConstants.FILE_READ_ACTION);

        System.out.println(perOne.implies(perAll));
        System.out.println(perAll.implies(perOne));
    }

    static void doPrivilegedAction(){
        // 用特权访问方式创建文件
        AccessController.doPrivileged(new PrivilegedAction<String>() {
            @Override
            public String run() {
                try {
                    File fs = new File("/Users/luliru/IdeaProjects/practice/test.txt");
                    fs.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
