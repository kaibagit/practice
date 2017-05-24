package com.kaiba.demo.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

/**
 * Created by luliru on 2017/5/24.
 */
public class VertxHttpServer {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        HttpServerOptions options = new HttpServerOptions().setMaxWebsocketFrameSize(1000000);
        HttpServer server = vertx.createHttpServer(options).requestHandler(request ->{
            request.response().end("Hello World,");
        });
        server.listen(9090,res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening! => "+res.cause());
            } else {
                System.out.println("Failed to bind! => "+res.cause());
            }
        });
    }
}
