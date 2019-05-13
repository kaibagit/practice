package com.luliru.vertx.demo.gateway;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * Created by luliru on 2018/7/9.
 */
public class Application {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();

//        GatewayHandler handler = new GatewayHandler();
//
//        Router router = Router.router(vertx);
//        router.get("/login").handler(null);
//
//        HttpServer server = vertx.createHttpServer();
//        server.requestHandler(handler).listen(8080);

        DeploymentOptions options = new DeploymentOptions().setInstances(4);

        vertx.deployVerticle(GatewayVerticle.class.getName(),options);
    }
}
