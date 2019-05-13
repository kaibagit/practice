package com.luliru.vertx.demo.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

/**
 * Created by luliru on 2018/7/12.
 */
public class RegisterDemo {

    public static void main(String[] args){
        register2();
    }

    static void register(){
        Vertx vertx = Vertx.vertx();

        EventBus eb = vertx.eventBus();

        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });
    }

    static void register2(){
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();

        MessageConsumer<String> consumer = eb.consumer("news.uk.sport");
        consumer.handler(message -> {
            System.out.println("I have received a message: " + message.body());
        });

        consumer.completionHandler(res -> {
            if (res.succeeded()) {
                System.out.println("The handler registration has reached all nodes");
            } else {
                System.out.println("Registration failed!");
            }
        });
    }

    static void unregister(){
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();

        MessageConsumer<String> consumer = eb.consumer("news.uk.sport");
        consumer.handler(message -> {
            System.out.println("I have received a message: " + message.body());
        });

        consumer.unregister(res -> {
            if (res.succeeded()) {
                System.out.println("The handler un-registration has reached all nodes");
            } else {
                System.out.println("Un-registration failed!");
            }
        });

    }
}
