package com.luliru.testing.lock.deadlock;

/**
 * Created by luliru on 2017/4/14.
 */
public class UndetectableDeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(){
            public void run(){
                try {
                    Class.forName("com.luliru.testing.lock.deadlock.ClassA");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread b = new Thread(){
            public void run(){
                try {
                    Class.forName("com.luliru.testing.lock.deadlock.ClassB");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        a.start();
        b.start();
        a.join();
        b.join();
    }
}
