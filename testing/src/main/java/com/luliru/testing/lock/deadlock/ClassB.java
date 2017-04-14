package com.luliru.testing.lock.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/4/14.
 */
public class ClassB {

    static {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.luliru.testing.lock.deadlock.ClassA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("ClassB init OK");
    }
}
