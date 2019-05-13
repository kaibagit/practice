package com.luliru.vertx.demo.rxjava;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;

public class SimpleHttpServer extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.requestStream().toFlowable().subscribe(req -> {
            req.response().putHeader("content-type", "text/html").end("<html><body><h1>Hello from vert.x!</h1></body></html>");
        });
        server.listen(8080);
    }

    public static void main(String[] args){
        Vertx vertx = io.vertx.reactivex.core.Vertx.vertx();
        vertx.deployVerticle("com.luliru.vertx.demo.rxjava.SimpleHttpServer");
    }

}
