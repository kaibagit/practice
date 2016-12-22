package com.kaiba.demo.runtime;

/**
 * Created by luliru on 2016/12/22.
 */
public class ShutdownHookTest {

    public static void main(String[] args){
        Thread shutdownHookOne = new Thread() {
            public void run() {
                System.out.println("shutdownHook one...");
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHookOne);

        Runnable threadOne = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread one doing something...");
            }
        };

        Runnable threadTwo = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread two doing something...");
            }
        };

        threadOne.run();
        threadTwo.run();
    }
}
