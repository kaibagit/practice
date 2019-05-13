package com.luliru.vertx.demo.http.web;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;

/**
 * Created by luliru on 2018/7/26.
 */
public class SessionDemo {

    void localSession(){
        Vertx vertx = Vertx.vertx();

        SessionStore store1 = LocalSessionStore.create(vertx);

        // Create a local session store specifying the local shared map name to use
        // This might be useful if you have more than one application in the same
        // Vert.x instance and want to use different maps for different applications
        SessionStore store2 = LocalSessionStore.create(vertx, "myapp3.sessionmap");

        // Create a local session store specifying the local shared map name to use and
        // setting the reaper interval for expired sessions to 10 seconds
        SessionStore store3 = LocalSessionStore.create(vertx, "myapp3.sessionmap", 10000);
    }

    void clusterSession(){
        Vertx.clusteredVertx(new VertxOptions().setClustered(true), res -> {

            Vertx vertx = res.result();

            // Create a clustered session store using defaults
            SessionStore store1 = ClusteredSessionStore.create(vertx);

            // Create a clustered session store specifying the distributed map name to use
            // This might be useful if you have more than one application in the cluster
            // and want to use different maps for different applications
            SessionStore store2 = ClusteredSessionStore.create(vertx, "myclusteredapp3.sessionmap");
        });
    }

    void sessionHandler(){
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        // We need a cookie handler first
        router.route().handler(CookieHandler.create());

        // Create a clustered session store using defaults
        SessionStore store = ClusteredSessionStore.create(vertx);

        SessionHandler sessionHandler = SessionHandler.create(store);

        // Make sure all requests are routed through the session handler too
        router.route().handler(sessionHandler);

        // Now your application handlers
        router.route("/somepath/blah/").handler(routingContext -> {

            Session session = routingContext.session();
            session.put("foo", "bar");
            // etc

        });
    }

    void useSession(Router router,SessionHandler sessionHandler){
        router.route().handler(CookieHandler.create());
        router.route().handler(sessionHandler);

        // Now your application handlers
        router.route("/somepath/blah").handler(routingContext -> {

            Session session = routingContext.session();

            // Put some data from the session
            session.put("foo", "bar");

            // Retrieve some data from a session
            int age = session.get("age");

            // Remove some data from a session
            JsonObject obj = session.remove("myobj");

        });
    }
}
