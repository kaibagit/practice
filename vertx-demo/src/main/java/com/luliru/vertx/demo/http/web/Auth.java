package com.luliru.vertx.demo.http.web;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;

/**
 * Created by luliru on 2018/7/26.
 */
public class Auth {

    void test(Router router,Vertx vertx,AuthProvider authProvider){
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        AuthHandler basicAuthHandler = BasicAuthHandler.create(authProvider);
    }

    void test2(Router router,Vertx vertx,AuthProvider authProvider){
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
        router.route().handler(UserSessionHandler.create(authProvider));

        AuthHandler basicAuthHandler = BasicAuthHandler.create(authProvider);

        // All requests to paths starting with '/private/' will be protected
        router.route("/private/*").handler(basicAuthHandler);

        router.route("/someotherpath").handler(routingContext -> {

            // This will be public access - no login required

        });

        router.route("/private/somepath").handler(routingContext -> {

            // This will require a login

            // This will have the value true
            boolean isAuthenticated = routingContext.user() != null;

        });
    }

    void direct(Router router,Vertx vertx,AuthProvider authProvider){
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
        router.route().handler(UserSessionHandler.create(authProvider));

        AuthHandler redirectAuthHandler = RedirectAuthHandler.create(authProvider);

        // All requests to paths starting with '/private/' will be protected
        router.route("/private/*").handler(redirectAuthHandler);

        // Handle the actual login
        router.route("/login").handler(FormLoginHandler.create(authProvider));

        // Set a static server to serve static resources, e.g. the login page
        router.route().handler(StaticHandler.create());

        router.route("/someotherpath").handler(routingContext -> {
            // This will be public access - no login required
        });

        router.route("/private/somepath").handler(routingContext -> {

            // This will require a login

            // This will have the value true
            boolean isAuthenticated = routingContext.user() != null;

        });
    }

    void jwt(Vertx vertx){
        Router router = Router.router(vertx);

        JsonObject authConfig = new JsonObject().put("keyStore", new JsonObject()
                .put("type", "jceks")
                .put("path", "keystore.jceks")
                .put("password", "secret"));

//        JWTAuth authProvider = JWTAuth.create(vertx, authConfig);
//
//        router.route("/login").handler(ctx -> {
//            // this is an example, authentication should be done with another provider...
//            if ("paulo".equals(ctx.request().getParam("username")) && "secret".equals(ctx.request().getParam("password"))) {
//                ctx.response().end(authProvider.generateToken(new JsonObject().put("sub", "paulo"), new JWTOptions()));
//            } else {
//                ctx.fail(401);
//            }
//        });
    }
}
