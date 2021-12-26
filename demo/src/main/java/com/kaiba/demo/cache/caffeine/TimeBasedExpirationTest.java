package com.kaiba.demo.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * TimeBasedExpirationTest
 * Created by luliru on 12/26/21.
 */
@Slf4j
public class TimeBasedExpirationTest {

    public static void main(String[] args) throws InterruptedException {
        // Evict based on a fixed expiration policy
        LoadingCache<String, Integer> graphs = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(key -> {
                    log.info("load {}", key);
                    return key.hashCode();
                });

        graphs.get("hello");
        Thread.sleep(4000L);
        graphs.get("hello");
        Thread.sleep(1000L);
        graphs.get("hello");

//        LoadingCache<String, Integer> graphs = Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .build(key -> key.hashCode());

        // Evict based on a varying expiration policy
//        LoadingCache<String, Integer> graphs = Caffeine.newBuilder()
//                .expireAfter(new Expiry<String, Integer>() {
//                    public long expireAfterCreate(String key, Integer graph, long currentTime) {
//                        return TimeUnit.SECONDS.toNanos(3);
//                    }
//                    public long expireAfterUpdate(String key, Integer graph,
//                                                  long currentTime, long currentDuration) {
//                        return currentDuration;
//                    }
//                    public long expireAfterRead(String key, Integer graph,
//                                                long currentTime, long currentDuration) {
//                        return currentDuration;
//                    }
//                })
//                .build(key -> key.hashCode());
    }
}
