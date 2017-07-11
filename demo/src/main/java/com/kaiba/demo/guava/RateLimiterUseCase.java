package com.kaiba.demo.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/7/11.
 */
public class RateLimiterUseCase {

    private Logger logger = LoggerFactory.getLogger(RateLimiterUseCase.class);
    private int qps;
    private int requestCount;

    private RateLimiter rateLimiter;

    public RateLimiterUseCase(int qps, int requestCount) {
        this.qps = qps;
        this.requestCount = requestCount;
    }

    private void buildRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    private void processRequest(int requestCount) {
        logger.info("RateLimiter 类型:{}", rateLimiter.getClass());
        long startTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < requestCount; i++) {
            rateLimiter.acquire();
        }
        long usedTimeMillis = System.currentTimeMillis() - startTimeMillis;
        logger.info("处理请求数:{},耗时:{},限流的qps:{},实际的qps:{}", requestCount, usedTimeMillis, rateLimiter.getRate(), Math.ceil(requestCount / (usedTimeMillis / 1000.00)));
        logger.info("");
    }

    private void sleep(int sleepTimeSeconds) {
        logger.info("等待{}秒后，继续处理下一批{}个请求", sleepTimeSeconds, requestCount);
        try {
            Thread.sleep(sleepTimeSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processWithTokenBucket() {
        buildRateLimiter(RateLimiter.create(qps));
        doProcess();
    }

    public void processWithLeakBucket() {
        buildRateLimiter(RateLimiter.create(qps, 0, TimeUnit.MILLISECONDS));
        doProcess();
    }

    private void doProcess() {

        sleep(0);
        processRequest(requestCount);

        sleep(1);
        processRequest(requestCount);

        sleep(5);
        processRequest(requestCount);

        sleep(10);
        processRequest(requestCount);
    }


    public static void main(String[] args) {
        RateLimiterUseCase test = new RateLimiterUseCase(10, 100);
        test.processWithLeakBucket();
        test.processWithTokenBucket();

        test = new RateLimiterUseCase(10, 15);
        test.processWithLeakBucket();
        test.processWithTokenBucket();
    }
}
