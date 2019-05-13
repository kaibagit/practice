package com.luliru.vertx.demo.http.web;

import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CookieHandler;

/**
 * Created by luliru on 2018/7/26.
 */
public class CookieDemo {

    void handle(Router router){
        router.route().handler(CookieHandler.create());

        router.route("some/path/").handler(routingContext -> {

            Cookie someCookie = routingContext.getCookie("mycookie");
            String cookieValue = someCookie.getValue();

            // Do something with cookie...

            // Add a cookie - this will get written back in the response automatically
            routingContext.addCookie(Cookie.cookie("othercookie", "somevalue"));
        });
    }

}
