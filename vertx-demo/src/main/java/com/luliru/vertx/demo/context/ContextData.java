package com.luliru.vertx.demo.context;

import io.vertx.core.Context;
import io.vertx.core.Vertx;

public class ContextData {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        Context context = vertx.getOrCreateContext();
        context.put("data", "hello");
        context.runOnContext((v) -> {
            String hello = context.get("data");
        });
    }
}
