package com.luliru.vertx.demo.rxjava;

import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpClient;
import io.vertx.reactivex.core.http.HttpClientRequest;

public class SimpleHttpClient extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        HttpClient client = vertx.createHttpClient();
        HttpClientRequest req = client.request(HttpMethod.GET, 8080, "localhost", "/");
        req.toFlowable().

                // Status code check and -> Flowable<Buffer>
                        flatMap(resp -> {
                    if (resp.statusCode() != 200) {
                        throw new RuntimeException("Wrong status code " + resp.statusCode());
                    }
                    return resp.toFlowable();
                }).

                subscribe(data -> System.out.println("Server content " + data.toString("UTF-8")));

        // End request
        req.end();
    }

    public static void main(String[] args){
        Vertx vertx = io.vertx.reactivex.core.Vertx.vertx();
        vertx.deployVerticle("com.luliru.vertx.demo.rxjava.SimpleHttpClient");
    }
}
