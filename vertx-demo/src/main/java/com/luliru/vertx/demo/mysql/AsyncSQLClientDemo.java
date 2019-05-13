package com.luliru.vertx.demo.mysql;

import com.luliru.vertx.demo.jdbc.JdbcDemo;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/7/16.
 */
public class AsyncSQLClientDemo {

    private static final Logger log = LoggerFactory.getLogger(AsyncSQLClientDemo.class);

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();

        JsonObject mySQLClientConfig = new JsonObject()
                .put("host", "localhost")
                .put("port",3306)
                .put("maxPoolSize",5)
                .put("username","root")
                .put("password",(String)null)
                .put("database","test");

        AsyncSQLClient client = MySQLClient.createShared(vertx, mySQLClientConfig);

        client.getConnection(res -> {
            if (res.succeeded()) {

                SQLConnection connection = res.result();

                // 获得一个连接
                log.info(connection.toString());
            } else {
                // 获取连接失败 - 处理异常
                log.info("getConnection fail",res.cause());
            }
        });

    }
}
