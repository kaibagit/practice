package com.kaiba.demo.java8.fp.function;

import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args){
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        //执行完F后再执行F2的Accept方法
//        f.andThen(f2).accept("test");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f).accept("test1");
    }
}
