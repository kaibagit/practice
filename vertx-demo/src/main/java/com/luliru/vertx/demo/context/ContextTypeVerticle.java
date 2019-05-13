package com.luliru.vertx.demo.context;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;

public class ContextTypeVerticle extends AbstractVerticle {

    public void start() {
        Context context = vertx.getOrCreateContext();
        if (context.isEventLoopContext()) {
            System.out.println("Context attached to Event Loop");
        } else if (context.isWorkerContext()) {
            System.out.println("Context attached to Worker Thread");
        } else if (context.isMultiThreadedWorkerContext()) {
            System.out.println("Context attached to Worker Thread - multi threaded worker");
        } else if (! Context.isOnVertxThread()) {
            System.out.println("Context not attached to a thread managed by vert.x");
        }
    }

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("com.luliru.vertx.demo.context.ContextTypeVerticle");
    }
}
