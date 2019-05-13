package com.luliru.vertx.demo.http.web;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.TimeoutHandler;

/**
 * Created by luliru on 2018/7/26.
 */
public class Timeout {

    void test(Router router){
        router.route("/foo/").handler(TimeoutHandler.create(5000));
    }
}
