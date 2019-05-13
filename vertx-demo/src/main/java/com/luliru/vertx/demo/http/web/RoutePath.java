package com.luliru.vertx.demo.http.web;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class RoutePath {

    public static void main(String[] args){

    }

    static void path_1(Router router){
        Route route = router.route().path("/some/path/*");

        route.handler(routingContext -> {
            // This handler will be called for any path that starts with
            // `/some/path/`, e.g.

            // `/some/path`
            // `/some/path/`
            // `/some/path/subdir`
            // `/some/path/subdir/blah.html`
            //
            // but not:
            // `/some/bath`
        });
    }

    static void path_2(Router router){
        Route route = router.route("/some/path/*");

        route.handler(routingContext -> {
            // This handler will be called same as previous example
        });
    }

    static void path_3(Router router){
        Route route = router.route(HttpMethod.POST, "/catalogue/products/:productype/:productid/");

        route.handler(routingContext -> {

            String productType = routingContext.request().getParam("producttype");
            String productID = routingContext.request().getParam("productid");

            // Do something with them...
        });
    }

    static void path_4(Router router){
        Route route = router.route().pathRegex(".*foo");

        route.handler(routingContext -> {

            // This handler will be called for:

            // /some/path/foo
            // /foo
            // /foo/bar/wibble/foo
            // /foo/bar

            // But not:
            // /bar/wibble
        });


        route = router.routeWithRegex(".*foo");

        route.handler(routingContext -> {

            // This handler will be called same as previous example

        });
    }

    static void path_5(Router router){
        Route route = router.routeWithRegex(".*foo");

        // This regular expression matches paths that start with something like:
        // "/foo/bar" - where the "foo" is captured into param0 and the "bar" is captured into
        // param1
        route.pathRegex("\\/([^\\/]+)\\/([^\\/]+)").handler(routingContext -> {

            String productType = routingContext.request().getParam("param0");
            String productID = routingContext.request().getParam("param1");

            // Do something with them...
        });
    }

    static void path_6(Router router){
        Route route = router.route().method(HttpMethod.POST);

        route.handler(routingContext -> {

            // This handler will be called for any POST request

        });
    }

    static void path_7(Router router){
        Route route = router.route(HttpMethod.POST, "/some/path/");

        route.handler(routingContext -> {

            // This handler will be called for any POST request to a URI path starting with /some/path/

        });
    }

    static void path_8(Router router){
        router.get().handler(routingContext -> {

            // Will be called for any GET request

        });

        router.get("/some/path/").handler(routingContext -> {

            // Will be called for any GET request to a path
            // starting with /some/path

        });

        router.getWithRegex(".*foo").handler(routingContext -> {

            // Will be called for any GET request to a path
            // ending with `foo`

        });
    }

    static void path_9(Router router){
        Route route = router.route().method(HttpMethod.POST).method(HttpMethod.PUT);

        route.handler(routingContext -> {

            // This handler will be called for any POST or PUT request

        });
    }
}
