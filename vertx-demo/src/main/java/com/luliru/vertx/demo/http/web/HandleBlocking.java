package com.luliru.vertx.demo.http.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class HandleBlocking {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/some/path/").blockingHandler(routingContext -> {

            // Do something that might take some time synchronously
            //service.doSomethingThatBlocks();

            // Now call the next handler
            routingContext.next();
        });

        server.requestHandler(router::accept).listen(8080);
    }
}
