package com.kaiba.demo.java8.fp.function;

@FunctionalInterface
public interface MyFunction<T, R> {

    R apply(T t);

//    R then(T t);
}
