package com.luliru.vertx.demo.rxjava;

import io.reactivex.Single;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.ext.web.codec.BodyCodec;

public class SimpleClient extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        WebClient client = WebClient.create(vertx);
        Single<HttpResponse<String>> request = client.get(8080, "localhost", "/")
                .as(BodyCodec.string())
                .rxSend();

        // Fire the request
        request.subscribe(resp -> System.out.println("Server content " + resp.body()));

        // Again
        request.subscribe(resp -> System.out.println("Server content " + resp.body()));

        // And again
        request.subscribe(resp -> System.out.println("Server content " + resp.body()));
    }

    public static void main(String[] args){
        Vertx vertx = io.vertx.reactivex.core.Vertx.vertx();
        vertx.deployVerticle("com.luliru.vertx.demo.rxjava.SimpleClient");
    }

}
