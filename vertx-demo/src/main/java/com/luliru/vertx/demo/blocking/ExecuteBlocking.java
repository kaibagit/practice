package com.luliru.vertx.demo.blocking;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/7/10.
 */
public class ExecuteBlocking {

    private static final Logger log = LoggerFactory.getLogger(ExecuteBlocking.class);

    public static void main(String[] args){
        serial();
    }

    static void serial(){
        Vertx vertx = Vertx.vertx();

        for(int i=0;i<10;i++){
            int index = i;
            vertx.executeBlocking(future -> {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                future.complete("hello,"+index);
            }, false,res -> {
                log.info("The result is: " + res.result());
            });
        }
    }
}
