package com.kaiba.demo.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * AsyncCommandDemo
 * Created by luliru on 2019-12-20.
 */
public class AsyncCommandDemo {

    private static final Logger log = LoggerFactory.getLogger(AsyncCommandDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testAsyncSetAndGet2();
    }

    private static RedisAsyncCommands<String,String> getCommands(){
        RedisURI redisUri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisAsyncCommands<String,String> commands = connection.async();
        return commands;
    }

    private static void first() throws ExecutionException, InterruptedException {
        RedisAsyncCommands<String,String> commands = getCommands();
        RedisFuture<String> redisFuture = commands.ping();
        log.info("Ping result:{}", redisFuture.get());
    }

    private static void testAsyncSetAndGet1() throws ExecutionException, InterruptedException {
        RedisAsyncCommands<String,String> commands = getCommands();
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        RedisFuture<String> future = commands.set("name", "throwable", setArgs);
        // CompletableFuture#thenAccept()
        future.thenAccept(value -> log.info("Set命令返回:{}", value));
        // Future#get()
        future.get();
    }

    private static void testAsyncSetAndGet2() throws ExecutionException, InterruptedException {
        RedisAsyncCommands<String,String> commands = getCommands();
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        CompletableFuture<Void> result =
                (CompletableFuture<Void>) commands.set("name", "throwable", setArgs)
                        .thenAcceptBoth(commands.get("name"),
                                (s, g) -> {
                                    log.info("Set命令返回:{}", s);
                                    log.info("Get命令返回:{}", g);
                                });
        result.get();
    }
}
