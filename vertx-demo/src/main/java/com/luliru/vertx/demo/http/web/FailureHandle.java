package com.luliru.vertx.demo.http.web;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class FailureHandle {

    void handle(Router router){
        Route route1 = router.get("/somepath/path1/");

        route1.handler(routingContext -> {

            // Let's say this throws a RuntimeException
            throw new RuntimeException("something happened!");

        });

        Route route2 = router.get("/somepath/path2");

        route2.handler(routingContext -> {

            // This one deliberately fails the request passing in the status code
            // E.g. 403 - Forbidden
            routingContext.fail(403);

        });

        // Define a failure handler
        // This will get called for any failures in the above handlers
        Route route3 = router.get("/somepath/*");

        route3.failureHandler(failureRoutingContext -> {

            int statusCode = failureRoutingContext.statusCode();

            // Status code will be 500 for the RuntimeException or 403 for the other failure
            HttpServerResponse response = failureRoutingContext.response();
            response.setStatusCode(statusCode).end("Sorry! Not today");

        });
    }
}
