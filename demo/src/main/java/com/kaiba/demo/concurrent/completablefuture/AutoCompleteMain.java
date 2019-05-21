package com.kaiba.demo.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created by luliru on 2017/6/5.
 */
public class AutoCompleteMain {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> f = new CompletableFuture<>();
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000L);
                    f.complete(100);
//                    f.completeExceptionally(new Exception());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        System.out.println(f.get());
    }
}
