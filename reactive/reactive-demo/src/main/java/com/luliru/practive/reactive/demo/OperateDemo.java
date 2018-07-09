package com.luliru.practive.reactive.demo;

import reactor.core.publisher.Flux;

/**
 * Created by luliru on 2018/7/9.
 */
public class OperateDemo {

    static void buffer(){
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        Flux.intervalMillis(100).bufferMillis(1001).take(2).toStream().forEach(System.out::println);
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }

    static void filter(){
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
    }
}
