package com.luliru.practive.reactive.demo;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Created by luliru on 2018/7/9.
 */
public class SchedulerDemo {

    static void switchThreads(){
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
        .publishOn(Schedulers.single())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .publishOn(Schedulers.elastic())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .subscribeOn(Schedulers.parallel())
        .toStream()
        .forEach(System.out::println);
    }
}
