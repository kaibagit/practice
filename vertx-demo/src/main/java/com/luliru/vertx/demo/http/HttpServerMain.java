package com.luliru.vertx.demo.http;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * Created by luliru on 2018/5/3.
 */
public class HttpServerMain {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            request.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World!");
        }).listen(8080);
    }
}
