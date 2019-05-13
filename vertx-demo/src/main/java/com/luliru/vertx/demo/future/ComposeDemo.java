package com.luliru.vertx.demo.future;

import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * Created by luliru on 2018/7/16.
 */
public class ComposeDemo {

    private static final Logger log = LoggerFactory.getLogger(ComposeDemo.class);

    public static void main(String[] args){
        // 串行逻辑
//         try{
//             conn = getConnection()
//             query()
//             log.info(rs)
//         }finally {
//             try{
//                 if(conn != null){
//                     close();
//                     log.info("执行成功");
//                 }
//             }catch (Exception e){
//                 log.error("失败",e);
//             }
//
//         }

        test11();

    }

    public static void test(){
        Vertx vertx = Vertx.vertx();
        JsonObject config = new JsonObject()
                .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                .put("jdbcUrl", "jdbc:mysql://localhost:3306/disconf")
                .put("driverClassName", "com.mysql.jdbc.Driver")
                .put("maximumPoolSize", 5)
                .put("username","root")
                .put("password","");

        JDBCClient client = JDBCClient.createShared(vertx, config,"MyDataSource");

        Future<SQLConnection> future = Future.future();

        client.getConnection(future.completer());
        future.compose(conn ->{
            Future<ResultSet> fut = Future.future();
            conn.query("SELECT * FROM user",fut.completer());
            return fut;
        }).compose(rs ->{
            Future<SQLConnection> fut = Future.future();
            log.info(rs.toJson().toString());
            return fut;
        }).compose(conn ->{
            Future<Void> fut = Future.future();
            conn.close(fut);
            return fut;
        }).setHandler(res ->{
            if(res.succeeded()){
                log.info("success");
            }else {
                log.error("fail",res.cause());
            }
        });
    }

    static void test11(){
        Vertx vertx = Vertx.vertx();
        JsonObject config = new JsonObject()
                .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                .put("jdbcUrl", "jdbc:mysql://localhost:3306/disconf")
                .put("driverClassName", "com.mysql.jdbc.Driver")
                .put("maximumPoolSize", 5)
                .put("username","root")
                .put("password","");

        JDBCClient client = JDBCClient.createShared(vertx, config,"MyDataSource");

        Future<SQLConnection> future = Future.future();
        client.getConnection(future.completer());
        Future<Void> future_log = future.compose(conn ->{
            Future<ResultSet> fut = Future.future();
            conn.query("SELECT * FROM user",fut.completer());
            return fut;
        }).compose(rs ->{
            Future<Void> fut = Future.future();
            log.info(rs.toJson().toString());
            fut.complete();
            return fut;
        });
        CompositeFuture.join(future,future_log).compose(res ->{
            Future<Void> fut = Future.future();
            if(future.succeeded()){
                future.result().close(fut.completer());
            }else{
                fut.complete();
            }
            return fut;
        }).setHandler(res ->{
            if(res.succeeded()){
                log.info("执行成功");
            }else{
                log.error("失败",res.cause());
            }
        });
//        CompositeFuture.join(future,future_log).setHandler(res->{
//            if(future.succeeded()){
//                future.result().close(res2 -> {
//
//                });
//            }
//        });
    }

    public static void test2(){
        Vertx vertx = Vertx.vertx();
        JsonObject config = new JsonObject()
                .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                .put("jdbcUrl", "jdbc:mysql://localhost:3306/disconf")
                .put("driverClassName", "com.mysql.jdbc.Driver")
                .put("maximumPoolSize", 5)
                .put("username","root")
                .put("password","");

        JDBCClient client = JDBCClient.createShared(vertx, config,"MyDataSource");

        Future<SQLConnection> future = Future.future();

        client.getConnection(future.completer());
        future.compose(new Function<SQLConnection, Future<ResultSet>>(){
            public Future<ResultSet> apply(SQLConnection conn){
                Future<ResultSet> fut = Future.future();
                conn.query("SELECT * FROM user",fut.completer());
                return fut;
            };
        }).compose(new Function<ResultSet, Future<SQLConnection>>(){
            public Future<SQLConnection> apply(ResultSet rs){
                Future<SQLConnection> fut = Future.future();
                log.info(rs.toJson().toString());
                return fut;
            };
        }).compose(new Function<SQLConnection, Future<Void>>(){
            public Future<Void> apply(SQLConnection conn){
                Future<Void> fut = Future.future();
                conn.close(fut);
                return fut;
            };
        }).setHandler(new Handler<AsyncResult<Void>>(){
            public void handle(AsyncResult<Void> res){
                if(res.succeeded()){
                    log.info("success");
                }else {
                    log.error("fail",res.cause());
                }
            }
        });
    }
}
