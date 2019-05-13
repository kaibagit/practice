package com.luliru.vertx.demo.http.web;

import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class RouteMime {

    void one(Router router){
        router.route().consumes("text/html").handler(routingContext -> {

            // This handler will be called for any request with
            // content-type header set to `text/html`

        });

        router.route().consumes("text/*").handler(routingContext -> {

            // This handler will be called for any request with top level type `text`
            // e.g. content-type header set to `text/html` or `text/plain` will both match

        });

        router.route().consumes("*/json").handler(routingContext -> {

            // This handler will be called for any request with sub-type json
            // e.g. content-type header set to `text/json` or `application/json` will both match

        });
    }

    void multi(Router router){
        router.route().consumes("text/html").consumes("text/plain").handler(routingContext -> {

            // This handler will be called for any request with
            // content-type header set to `text/html` or `text/plain`.

        });
    }
}
