package com.kaiba.demo.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/7/22.
 */
public class SmoothWarmingUpTest {

    private static final Logger log = LoggerFactory.getLogger(SmoothWarmingUpTest.class);

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(5, 2000, TimeUnit.MILLISECONDS);
        Thread.sleep(3000L);
        int index = 0;
        long lastTime = System.currentTimeMillis();
        for(int i = 1; i <= 20;i++) {
            limiter.acquire();
            index++;
            long newTime = System.currentTimeMillis();
            log.info(Integer.toString(index)+":"+Long.toString(newTime-lastTime));
            lastTime = newTime;
        }

        log.info("==================");

        Thread.sleep(3000L);
        index = 0;
        lastTime = System.currentTimeMillis();
        for(int i = 1; i <= 10;i++) {
            limiter.acquire();
            index++;
            long newTime = System.currentTimeMillis();
            log.info(Integer.toString(index)+":"+Long.toString(newTime-lastTime));
            lastTime = newTime;
        }
    }
}
