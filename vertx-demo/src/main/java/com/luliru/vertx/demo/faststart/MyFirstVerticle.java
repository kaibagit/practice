package com.luliru.vertx.demo.faststart;

import io.vertx.core.AbstractVerticle;

/**
 * Created by luliru on 2018/5/3.
 */
public class MyFirstVerticle extends AbstractVerticle {
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World!");
        }).listen(8080);
    }
}

