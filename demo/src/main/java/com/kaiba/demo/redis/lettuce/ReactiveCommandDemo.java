package com.kaiba.demo.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * ReactiveCommandDemo
 * Created by luliru on 2019-12-20.
 */
public class ReactiveCommandDemo {

    private static final Logger log = LoggerFactory.getLogger(ReactiveCommandDemo.class);

    public static void main(String[] args) {

    }

    private static RedisReactiveCommands<String, String> getCommands(){
        RedisURI redisUri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisReactiveCommands<String, String> commands = connection.reactive();
        return commands;
    }

    private void testReactivePing() throws Exception {
        RedisReactiveCommands<String, String> commands = getCommands();

        Mono<String> ping = commands.ping();
        ping.subscribe(v -> log.info("Ping result:{}", v));
        Thread.sleep(1000);
    }
// Ping result:PONG

    private void testReactiveSetAndGet() throws Exception {
        RedisReactiveCommands<String, String> commands = getCommands();

        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        commands.set("name", "throwable", setArgs).block();
        commands.get("name").subscribe(value -> log.info("Get命令返回:{}", value));
        Thread.sleep(1000);
    }
// Get命令返回:throwable

    private void testReactiveSet() throws Exception {
        RedisReactiveCommands<String, String> commands = getCommands();

        commands.sadd("food", "bread", "meat", "fish").block();
        Flux<String> flux = commands.smembers("food");
        flux.subscribe(log::info);
        commands.srem("food", "bread", "meat", "fish").block();
        Thread.sleep(1000);
    }
}
