package com.kaiba.demo.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luliru on 2017/8/2.
 */
public class ShutdownTest {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("begin");
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finish");
            }
        };
        executorService.submit(runnable);
        executorService.shutdown();     //直接返回
        System.out.println("main finish");
    }
}
