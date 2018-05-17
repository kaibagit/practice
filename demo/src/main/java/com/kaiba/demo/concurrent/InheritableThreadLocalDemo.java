package com.kaiba.demo.concurrent;

/**
 * Created by luliru on 2018/3/21.
 */
public class InheritableThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
//        testThreadLocal();
        testInheritableThreadLocal();
    }

    private static void testThreadLocal(){
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("name");
        new Thread(){
            public void run(){
                System.out.println(threadLocal.get());      // ==> null
            }
        }.start();
    }

    private static void testInheritableThreadLocal() throws InterruptedException {
        InheritableThreadLocal<String> threadLocal_1 = new InheritableThreadLocal<>();
        threadLocal_1.set("name");
        InheritableThreadLocal<String> threadLocal_2 = new InheritableThreadLocal<>();
        new Thread(){
            public void run(){
                System.out.println(threadLocal_1.get());      // ==> name
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadLocal_2.get());    // ==> null
            }
        }.start();
        threadLocal_2.set("name2");
        Thread.sleep(5000L);
        System.out.println(threadLocal_2.get());            // ==> name2
    }
}
