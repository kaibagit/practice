package com.kaiba.demo.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by luliru on 2017/2/24.
 */
public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        testNoRateLimiter();
        testWithRateLimiter();
        testBursts();
    }

    public static void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    public static void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    public static void testBursts() throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(2.0);
        Thread.sleep(1000L);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

}
