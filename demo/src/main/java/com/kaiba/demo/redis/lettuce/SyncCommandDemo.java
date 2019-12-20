package com.kaiba.demo.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * SyncCommandDemo
 * Created by luliru on 2019-12-20.
 */
public class SyncCommandDemo {

    private static final Logger log = LoggerFactory.getLogger(SyncCommandDemo.class);

    public static void main(String[] args) {
        RedisURI redisUri = RedisURI.builder()                    // <1> 创建单机连接的连接信息
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);   // <2> 创建客户端
        StatefulRedisConnection<String, String> connection = redisClient.connect();     // <3> 创建线程安全的连接
        RedisCommands<String, String> redisCommands = connection.sync();                // <4> 创建同步命令
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        String result = redisCommands.set("name", "throwable", setArgs);
        log.info(result);
        result = redisCommands.get("name");
        log.info(result);
        // ... 其他操作
        connection.close();   // <5> 关闭连接
        redisClient.shutdown();  // <6> 关闭客户端
    }
}
