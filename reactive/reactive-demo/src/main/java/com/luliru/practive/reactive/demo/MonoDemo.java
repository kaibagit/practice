package com.luliru.practive.reactive.demo;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by luliru on 2018/7/9.
 */
public class MonoDemo {

    static void create(){
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }
}
