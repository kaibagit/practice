package com.kaiba.demo.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * ConnectionPoolDemo
 * Created by luliru on 2019-12-20.
 */
public class ConnectionPoolDemo {

    private static final Logger log = LoggerFactory.getLogger(ConnectionPoolDemo.class);

    public static void main(String[] args) throws Exception {
        RedisURI redisUri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        GenericObjectPool<StatefulRedisConnection<String, String>> pool
                = ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, poolConfig);
        try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) {
            RedisCommands<String, String> command = connection.sync();
            SetArgs setArgs = SetArgs.Builder.nx().ex(5);
            command.set("name", "throwable", setArgs);
            String n = command.get("name");
            log.info("Get value:{}", n);
        }
        pool.close();
        redisClient.shutdown();
    }
}
