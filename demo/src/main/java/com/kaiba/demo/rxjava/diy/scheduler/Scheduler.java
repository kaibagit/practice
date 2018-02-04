package com.kaiba.demo.rxjava.diy.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by luliru on 2018/2/4.
 */
public class Scheduler {

    final Executor executor;
    public Scheduler(Executor executor) {
        this.executor = executor;
    }
    public Worker createWorker() {
        return new Worker(executor);
    }
    public static class Worker {
        final Executor executor;
        public Worker(Executor executor) {
            this.executor = executor;
        }
        // 这里接受的是Runnable而不是Action0，其实这没什么关系，主要是懒得自定义函数式接口了。
        public void schedule(Runnable runnable) {
            executor.execute(runnable);
        }
    }
}