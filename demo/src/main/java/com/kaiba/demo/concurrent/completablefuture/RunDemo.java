package com.kaiba.demo.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by luliru on 2017/6/5.
 */
public class RunDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f =  future.thenRun(() -> System.out.println("finished"));
        System.out.println(f.get());
    }
}
