package com.kaiba.demo.java8.fp.function;

import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args){
        Predicate<String> p = o -> o.equals("test");
        Predicate<String> g = o -> o.startsWith("t");
        /**
         * negate: 用于对原来的Predicate做取反处理；
         * 如当调用p.test("test")为True时，调用p.negate().test("test")就会是False；
         */
        System.out.println(p.negate().test("test"));
        /**
         * and: 针对同一输入值，多个Predicate均返回True时返回True，否则返回False；
         */
        System.out.println(p.and(g).test("test"));
        /**
         * or: 针对同一输入值，多个Predicate只要有一个返回True则返回True，否则返回False
         */
        System.out.println(p.or(g).test("ta"));
    }
}
