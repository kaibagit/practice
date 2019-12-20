package com.kaiba.demo.redis.lettuce;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * ClientResourcesDemo
 * Created by luliru on 2019-12-20.
 */
public class ClientResourcesDemo {

    public static void main(String[] args) {
        // 默认
        // ClientResources resources = DefaultClientResources.create();

        // 建造器
        ClientResources resources = DefaultClientResources.builder()
                .ioThreadPoolSize(4)    //IO线程数
                .computationThreadPoolSize(4)   //任务线程数
                .build();

        RedisURI uri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();

        // 非集群
        RedisClient client = RedisClient.create(resources, uri);
        client.setOptions(ClientOptions.builder()
                .autoReconnect(true)
                .pingBeforeActivateConnection(true)
                .build());
        // 集群
        // RedisClusterClient clusterClient = RedisClusterClient.create(resources, uris);
    }
}
