package com.luliru.vertx.demo.rxjava.eventbus;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.eventbus.EventBus;

public class PingPong extends AbstractVerticle {

    private static final String ADDRESS = "ping-address";

    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        eb.consumer(ADDRESS)
                .toFlowable()
                .subscribe(message -> {
                    System.out.println("Received " + message.body());
                    message.reply("PONG");
                });

        // Send a message every second
        vertx.setPeriodic(1000, v -> {
            eb.rxSend(ADDRESS, "PING")
                    .subscribe(reply -> {
                        System.out.println("Received reply " + reply.body());
                    });
        });
    }

    public static void main(String[] args){
        Vertx vertx = io.vertx.reactivex.core.Vertx.vertx();
        vertx.deployVerticle("com.luliru.vertx.demo.rxjava.eventbus.PingPong");
    }
}
