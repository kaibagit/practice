package com.kaiba.demo.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by luliru on 2017/6/5.
 */
public class CombineDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        CompletableFuture<String> f =  future.thenCombine(future2, (x,y) -> y + "-" + x);
        System.out.println(f.get()); //abc-100
    }

}
