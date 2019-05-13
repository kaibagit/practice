package com.luliru.vertx.demo.jdbc;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/7/16.
 */
public class JdbcDemo {

    private static final Logger log = LoggerFactory.getLogger(JdbcDemo.class);

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        JsonObject config = new JsonObject()
                .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                .put("jdbcUrl", "jdbc:mysql://localhost:3306/disconf")
                .put("driverClassName", "com.mysql.jdbc.Driver")
                .put("maximumPoolSize", 5)
                .put("username","root")
                .put("password","");

        JDBCClient client = JDBCClient.createShared(vertx, config,"MyDataSource");

//        Future<SQLConnection> future = client.getConnection();
//        future
//                .compose(conn -> {
//                    // Return a future of ResultSet
//                    return selectProduct(conn);
//                })
//                // Return a collection of products by mapping
//                // each row to a Product
//                .map(result -> toProducts(result.getResults()))
//                .setHandler(ar -> {
//                    if (ar.failed()) { /* failure handling */ }
//                    else {
//                        ar.result().forEach(System.out::println);
//                    }
//                    connection.get().close(done -> {
//                        if (done.failed()) { /* failure handling */ }
//                    });
//                });
    }

    static void test1(){
        Vertx vertx = Vertx.vertx();
        JsonObject config = new JsonObject()
                .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                .put("jdbcUrl", "jdbc:mysql://localhost:3306/disconf")
                .put("driverClassName", "com.mysql.jdbc.Driver")
                .put("maximumPoolSize", 5)
                .put("username","root")
                .put("password","");

        JDBCClient client = JDBCClient.createShared(vertx, config,"MyDataSource");



        client.getConnection(res -> {
            if (res.succeeded()) {

                SQLConnection connection = res.result();

                connection.query("SELECT * FROM user", res2 -> {
                    if (res2.succeeded()) {

                        ResultSet rs = res2.result();
                        // 用结果集results进行其他操作
                        log.info(rs.toJson().toString());
                    }else{
                        log.error("query fail",res2.cause());
                    }
                });

                connection.close(done -> {
                    if (done.failed()) {
                        log.error("close fail",done.cause());
                    }
                });
            } else {
                // 获取连接失败 - 处理失败的情况
                log.error("getConnection fail",res.cause());
            }
        });
    }
}
