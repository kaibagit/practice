package com.luliru.vertx.demo.http.web;

import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class RoutingContextDemo {

    void contextData(Router router){
        router.get("/some/path").handler(routingContext -> {

            routingContext.put("foo", "bar");
            routingContext.next();

        });

        router.get("/some/path/other").handler(routingContext -> {

            String bar = routingContext.get("foo");
            // Do something with bar
            routingContext.response().end();

        });
    }
}
