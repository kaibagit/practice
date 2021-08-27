package com.kaiba.microserver.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * 信号量隔离
 * Created by luliru on 2017/2/21.
 */
@Slf4j
public class SemaphoreCommand extends HystrixCommand<String> {
    private final String name;
    public SemaphoreCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置信号量隔离方式,默认采用线程池隔离 */
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.name = name;
    }
    @Override
    protected String run() throws Exception {
        log.info("HystrixThread:" + Thread.currentThread().getName());
        return "HystrixThread:" + Thread.currentThread().getName();
    }
    public static void main(String[] args) throws Exception{
        SemaphoreCommand command = new SemaphoreCommand("semaphore");
        String result = command.execute();
        log.info(result);
        log.info("MainThread:" + Thread.currentThread().getName());
    }
}
