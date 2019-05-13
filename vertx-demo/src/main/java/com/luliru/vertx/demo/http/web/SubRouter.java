package com.luliru.vertx.demo.http.web;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/26.
 */
public class SubRouter {

    void subRouter_1(){
        Vertx vertx = Vertx.vertx();

        Router restAPI = Router.router(vertx);

        restAPI.get("/products/:productID").handler(rc -> {

            // TODO Handle the lookup of the product....
            rc.response().write("{'name':xxx}");

        });

        restAPI.put("/products/:productID").handler(rc -> {

            // TODO Add a new product...
            rc.response().end();

        });

        restAPI.delete("/products/:productID").handler(rc -> {

            // TODO delete the product...
            rc.response().end();

        });
    }

    void subRouter_2(){
        Vertx vertx = Vertx.vertx();
        Router mainRouter = Router.router(vertx);

        // Handle static resources
//        mainRouter.route("/static/*").handler(myStaticHandler);
//
//        mainRouter.route(".*\\.templ").handler(myTemplateHandler);
//
//        mainRouter.mountSubRouter("/productsAPI", restAPI);
    }
}
