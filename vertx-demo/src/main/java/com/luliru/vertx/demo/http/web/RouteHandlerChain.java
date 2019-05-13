package com.luliru.vertx.demo.http.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class RouteHandlerChain {

    public static void main(String[] args){
        chain();
    }

    static void chain(){
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        Route route1 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            // enable chunked responses because we will be adding data as
            // we execute over other handlers. This is only required once and
            // only if several handlers do output.
            response.setChunked(true);

            response.write("route1\n");

            // Call the next matching route after a 5 second delay
            routingContext.vertx().setTimer(5000, tid -> routingContext.next());
        }).handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            // enable chunked responses because we will be adding data as
            // we execute over other handlers. This is only required once and
            // only if several handlers do output.
            response.setChunked(true);

            response.write("route1.2\n");

            // Call the next matching route after a 5 second delay
            routingContext.vertx().setTimer(3000, tid -> routingContext.next());
        });

        Route route2 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route2\n");

            // Call the next matching route after a 5 second delay
            routingContext.vertx().setTimer(5000, tid ->  routingContext.next());
        });

        Route route3 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route3");

            // Now end the response
            routingContext.response().end();
        });

        server.requestHandler(router::accept).listen(8080);
    }

    static void seq(Router router){
        Route route1 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route1\n");

            // Now call the next matching route
            routingContext.next();
        });

        Route route2 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            // enable chunked responses because we will be adding data as
            // we execute over other handlers. This is only required once and
            // only if several handlers do output.
            response.setChunked(true);

            response.write("route2\n");

            // Now call the next matching route
            routingContext.next();
        });

        Route route3 = router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route3");

            // Now end the response
            routingContext.response().end();
        });

        // Change the order of route2 so it runs before route1
        route2.order(-1);
    }
}
