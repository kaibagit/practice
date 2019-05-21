package com.kaiba.demo.concurrent.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by luliru on 2017/6/5.
 */
public class CallbackAfterComplatedMain {

    private static Random rand = new Random();
    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute.");
        int value = rand.nextInt(1000);
        if(value > 500){
            throw new RuntimeException("value = "+value);
        }
        return value;
    }
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CallbackAfterComplatedMain::getMoreData);
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println(v);
            System.out.println(e);
        });
        System.out.println(f.get());
        System.in.read();
    }
}
