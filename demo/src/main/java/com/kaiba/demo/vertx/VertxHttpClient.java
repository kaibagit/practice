package com.kaiba.demo.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;

/**
 * Created by luliru on 2017/5/24.
 */
public class VertxHttpClient {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        HttpClientOptions options = new HttpClientOptions().setKeepAlive(false);
        HttpClient client = vertx.createHttpClient(options);

        client.getNow(9090, "localhost", "/some-uri", response -> {
            System.out.println("Received response with status code " + response.statusCode());
        });


        HttpClientRequest request = client.post(9090,"localhost","some-uri", response -> {
            System.out.println("Received response with status code " + response.statusCode());
        });
        request.putHeader("content-length", "1000");
        request.putHeader("content-type", "text/plain");
        request.write("nihao");
        request.end();
    }
}
