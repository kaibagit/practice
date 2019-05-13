package com.luliru.vertx.demo.gateway;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/7/9.
 */
public class GatewayVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(GatewayVerticle.class);

    public void start() throws Exception {
//        createTcpServer();
        createHttpServer();
    }

    private void createTcpServer(){
        NetServer server = vertx.createNetServer();
        server.listen(7070);
    }

    private void createHttpServer(){
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            String appid = request.getHeader("appid");
            request.bodyHandler(bodyHandler ->{
                String body = bodyHandler.toString();

                vertx.executeBlocking(future -> {
                    // 从redis读取数据
                    //String result = someAPI.blockingMethod("hello");
//                    future.complete(Boolean.FALSE);
                    future.fail(new Exception("redis异常"));
                }, res -> {
                    boolean authSuccess = true;
                    if(res.failed()){
                        log.error("未知异常",res.cause());
                    }else{
                        authSuccess = (boolean) res.result();
                    }
                    if(authSuccess){
                        request.response()
                                .putHeader("content-type", "text/plain").end("鉴权成功");
                    }else{
                        request.response()
                                .putHeader("content-type", "text/plain").end("鉴权失败");
                    }
                });
            });
        });
        server.listen(8080);
    }

    private void createHttpServerV2(){
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            String appid = request.getHeader("appid");
            request.bodyHandler(bodyHandler ->{
                String body = bodyHandler.toString();

                Future<Boolean> future = Future.future();



//                vertx.executeBlocking(future -> {
//                    // 从redis读取数据
//                    //String result = someAPI.blockingMethod("hello");
////                    future.complete(Boolean.FALSE);
//                    future.fail(new Exception("redis异常"));
//                }, res -> {
//                    boolean authSuccess = true;
//                    if(res.failed()){
//                        log.error("未知异常",res.cause());
//                    }else{
//                        authSuccess = (boolean) res.result();
//                    }
//                    if(authSuccess){
//                        request.response()
//                                .putHeader("content-type", "text/plain").end("鉴权成功");
//                    }else{
//                        request.response()
//                                .putHeader("content-type", "text/plain").end("鉴权失败");
//                    }
//                });
            });
        });
        server.listen(8080);
    }
}
