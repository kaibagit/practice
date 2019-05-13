package com.luliru.vertx.demo.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start() {
        System.out.println(Thread.currentThread().getName() +
                ": Hello Verticle : " +
                Thread.currentThread().getId());
    }

    public static void main(String[] args){
        DeploymentOptions options = new DeploymentOptions().setInstances(5);
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("com.luliru.vertx.demo.verticle.MyFirstVerticle",options);
    }
}
