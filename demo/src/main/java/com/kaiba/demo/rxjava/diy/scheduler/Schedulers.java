package com.kaiba.demo.rxjava.diy.scheduler;

import java.util.concurrent.Executors;

/**
 * Created by luliru on 2018/2/4.
 */
public class Schedulers {

    private static final Scheduler ioScheduler = new Scheduler(Executors.newCachedThreadPool());

    public static Scheduler io() {
        return ioScheduler;
    }
}
