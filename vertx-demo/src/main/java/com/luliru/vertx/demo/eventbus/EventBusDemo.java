package com.luliru.vertx.demo.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/7/12.
 */
public class EventBusDemo {

    private static final Logger log = LoggerFactory.getLogger(EventBusDemo.class);

    public static void main(String[] args){
        log.info("main");

        Vertx vertx = Vertx.vertx();

        EventBus eb = vertx.eventBus();

        eb.consumer("news.uk.sport", message -> {
            log.info("I have received a message: " + message.body());
        });

        eb.send("news.uk.sport", "Yay! Someone kicked a ball");

        DeliveryOptions options = new DeliveryOptions();
        options.addHeader("some-header", "some-value");
        eb.send("news.uk.sport", "Yay! Someone kicked a ball", options);

        eb.publish("news.uk.sport", "Yay! Someone kicked a ball");
    }
}
