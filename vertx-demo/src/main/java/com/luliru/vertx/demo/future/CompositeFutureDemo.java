package com.luliru.vertx.demo.future;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by luliru on 2018/7/17.
 */
public class CompositeFutureDemo {

    public static void main(String[] args){

    }

    /**
     * all composition waits until all futures are successful (or one fails)
     */
    static void all(){
        long start = System.currentTimeMillis();

        CompositeFuture.all(operationA(), operationA(), operationA()).setHandler((r) -> {
            if (r.succeeded()) {
                // You can even iterate all the results
                List<String> results = r.result().list();
                for (String result : results) {
                    System.out.println(result);
                }
                // This will still print max(operationA, operationB, operationC)
                System.out.println("Took me " + (System.currentTimeMillis() - start) + " millis");
            }
            else {
                System.out.println("Something went wrong");
            }
        });

    }

    /**
     * the any composition waits for the first succeeded future.
     */
    static void any(){
        CompositeFuture.any(operationA(), operationA()).setHandler(ar -> {
            if (ar.succeeded()) {
                // At least one is succeeded
            } else {
                // All failed
            }
        });
    }

    /**
     * The join composition waits until all futures are completed, either with a success or a failure.
     */
    static void join(){
        CompositeFuture.join(operationA(), operationA(), operationA()).setHandler(ar -> {
            if (ar.succeeded()) {
                // All succeeded
            } else {
                // All completed and at least one failed
            }
        });
    }

    // Return a future, then fulfill it after some time
    private static Future<String> operationA() {
        Future<String> future = Future.future();

        long millis = 1000 + ThreadLocalRandom.current().nextInt(500);
        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                future.complete("All is good " + millis);
            }
        },millis);

        return future;
    }
}
