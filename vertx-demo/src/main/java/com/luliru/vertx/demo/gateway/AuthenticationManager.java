//package com.luliru.vertx.demo.gateway;
//
//import io.vertx.core.Vertx;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * Created by luliru on 2018/7/9.
// */
//public class AuthenticationManager {
//
//    private static final Logger log = LoggerFactory.getLogger(AuthenticationManager.class);
//
//    private Vertx vertx;
//
//    public AuthenticationManager(Vertx vertx){
//        this.vertx = vertx;
//    }
//
//    public boolean isPass(String appid,String body){
//        return vertx.executeBlocking(future -> {
//            // 从redis读取数据
//            //String result = someAPI.blockingMethod("hello");
//            future.complete(Boolean.FALSE);
//        }, res -> {
//            System.out.println("The result is: " + res.result());
//            return res.result();
//        });
//    }
//}
