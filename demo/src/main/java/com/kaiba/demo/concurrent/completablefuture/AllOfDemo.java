package com.kaiba.demo.concurrent.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by luliru on 2017/6/5.
 */
public class AllOfDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random rand = new Random();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        CompletableFuture<Void> f =  CompletableFuture.allOf(future1,future2);
//        CompletableFuture<Object> f =  CompletableFuture.anyOf(future1,future2);
        System.out.println(f.get());
    }

}
