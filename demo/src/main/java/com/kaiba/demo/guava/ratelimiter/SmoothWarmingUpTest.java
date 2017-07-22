package com.kaiba.demo.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/7/22.
 */
public class SmoothWarmingUpTest {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        Thread.sleep(1000L);
        for(int i = 1; i <= 5;i++) {
            System.out.println(limiter.acquire());
        }
        Thread.sleep(1000L);
        for(int i = 1; i <= 5;i++) {
            System.out.println(limiter.acquire());
        }
    }
}
