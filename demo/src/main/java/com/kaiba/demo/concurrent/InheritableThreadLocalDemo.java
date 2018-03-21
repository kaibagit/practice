package com.kaiba.demo.concurrent;

/**
 * Created by luliru on 2018/3/21.
 */
public class InheritableThreadLocalDemo {

    public static void main(String[] args){
        testThreadLocal();
        testInheritableThreadLocal();
    }

    private static void testThreadLocal(){
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("name");
        new Thread(){
            public void run(){
                System.out.println(threadLocal.get());
            }
        }.start();
    }

    private static void testInheritableThreadLocal(){
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("name2");
        new Thread(){
            public void run(){
                System.out.println(threadLocal.get());
            }
        }.start();
    }
}
