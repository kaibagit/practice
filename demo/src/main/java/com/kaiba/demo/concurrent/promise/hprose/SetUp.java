package com.kaiba.demo.concurrent.promise.hprose;

import hprose.util.concurrent.Promise;
import hprose.util.concurrent.Rejector;
import hprose.util.concurrent.Resolver;

public class SetUp {

    public static void main(String[] args){

    }

    static void create(){
        Promise<String> promise = new Promise<>(() -> "hprose");
        promise.then((String value) -> System.out.println(value));
    }

    static void createErr(){
        Promise<?> promise = new Promise(() -> {
            throw new Exception("hprose");
        });
        promise.catchError((Throwable value) -> {
            System.out.println(value);
        });
    }

    static void resolve(){
        Promise<Integer> promise = new Promise<>(
                (Resolver<Integer> resolver, Rejector rejector) -> {
                    resolver.resolve(100);
                }
        );
        promise.then((Integer value) -> {
            System.out.println(value);
        });
    }

    public static void async() {
        System.out.println("before Promise constructor");
        Promise<String> promise = new Promise<>(() -> {
            Thread.sleep(100);
            System.out.println("running Promise constructor");
            return "promise from Promise constructor";
        });
        promise.then((String value) -> {
            System.out.println(value);
        });
        System.out.println("after Promise constructor");
    }
    public static void sync() {
        System.out.println("before Promise.sync");
        Promise<String> promise = Promise.sync(() -> {
            Thread.sleep(100);
            System.out.println("running Promise.sync");
            return "promise from Promise.sync";
        });
        promise.then((String value) -> {
            System.out.println(value);
        });
        System.out.println("after Promise.sync");
    }

    public static void delayed() {
        System.out.println(System.currentTimeMillis() + ": before Promise.delayed");
        Promise<String> promise = Promise.delayed(300, () -> {
            System.out.println(System.currentTimeMillis() + ": running Promise.delayed");
            return "promise from Promise.delayed";
        });
        promise.then((String value) -> {
            System.out.println(System.currentTimeMillis() + ": " + value);
        });
        System.out.println(System.currentTimeMillis() + ": after Promise.delayed");
    }
}
