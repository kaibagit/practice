package com.luliru.vertx.demo.gateway;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

/**
 * Created by luliru on 2018/7/9.
 */
public class GatewayHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest request) {
        request.handler(buffer ->{
            String body = buffer.toString();

        });

        System.out.println(Thread.currentThread()+" begin");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+" end");
        request.response()
                .putHeader("content-type", "text/plain")
                .end("Hello World!");
    }
}
