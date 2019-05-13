package com.luliru.vertx.demo.core;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/5/3.
 */
public class Periodic {

    private static final Logger log = LoggerFactory.getLogger(Periodic.class);

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(4));
        vertx.setPeriodic(5000, id -> {
            // This handler will get called every second
            System.out.println("timer fired!");
            log.info("timer fired!");
        });

        vertx.executeBlocking(future -> {
            // Call some blocking API that takes a significant amount of time to return
            String result = blockingMethod("hello");
            future.complete(result);
        }, res -> {
            System.out.println("The result is: " + res.result());
        });

    }

    private static String blockingMethod(String s){
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s+"!!!";
    }
}
