package com.kaiba.demo.java8;

import java.util.Optional;
import java.util.function.Function;

/**
 * OptionalTest
 * Created by luliru on 2019-06-17.
 */
public class OptionalTest {

    public static void main(String[] args){
        Function<Function<Integer,Integer>,Optional<Integer>> higherOrderFunc = (Function<Integer,Integer> a) -> Optional.of(2 + a.apply(2));
        // 在java中，函数是用对象模拟的，所以Java没法区分函数和对象
        // 对于Optional.of(x)，x是被当做对象（数据）处理的，所以会出现函数与函数作用，最后算出一个值
        Optional<Function<Integer,Integer>> functOptional = Optional.of(
                (Function<Integer, Integer>) integer -> integer * 3
        );
        Optional<Integer> result = functOptional.flatMap(higherOrderFunc);
        System.out.println(result);
    }
}
