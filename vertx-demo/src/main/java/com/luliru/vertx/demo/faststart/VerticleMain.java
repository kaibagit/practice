package com.luliru.vertx.demo.faststart;

import io.vertx.core.Vertx;

/**
 * Created by luliru on 2018/5/3.
 */
public class VerticleMain {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}
