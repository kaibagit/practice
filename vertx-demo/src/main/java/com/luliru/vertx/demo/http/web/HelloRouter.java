package com.luliru.vertx.demo.http.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class HelloRouter {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {

            // This handler will be called for every request

            HttpServerResponse response = routingContext.response();

            response.putHeader("content-type", "text/plain");

            // Write to the response and end it

            response.end("Hello World from Vert.x-Web!");

        });

        server.requestHandler(router::accept).listen(8080);
    }

    void enable(Router router){
        Route route = router.route(HttpMethod.PUT, "myapi/orders")
                .consumes("application/json")
                .produces("application/json");

        route.handler(routingContext -> {

            // This would be match for any PUT method to paths starting with "myapi/orders" with a
            // content-type of "application/json"
            // and an accept header matching "application/json"

        });

        route.disable();
        route.enable();
    }
}
