package com.luliru.vertx.demo.context;

import io.vertx.core.Context;
import io.vertx.core.Vertx;

public class ExecuteAsync {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        Context context = vertx.getOrCreateContext();
        System.out.println(Thread.currentThread().getName() + ","
                + Thread.currentThread().getId());
        context.runOnContext(v -> {
            System.out.println(Thread.currentThread().getName() + ","
                    + Thread.currentThread().getId()
                    + ", This will be executed async -> " + v);
        });
    }
}
