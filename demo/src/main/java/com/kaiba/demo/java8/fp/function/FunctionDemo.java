package com.kaiba.demo.java8.fp.function;

import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args){
        staticMethodTest();
    }

    static void basic(){
        Function<Integer, Integer> fn = x -> x * 3;
        System.out.println(fn.apply(2));
    }

    static void highOrderFunction(){
        // square x :: x * 3
        // square x :: x * x
        // compose :: (x -> y) -> (y -> z) -> Integer
        // compose f1 f2 z = f1.f2 z
        // compose . square . triple 2

        Function<Function<Integer, Integer>,
                Function<Function<Integer, Integer>,
                        Function<Integer, Integer>>> compose =
                x -> y -> z -> x.apply(y.apply(z));
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Integer> square = x -> x * x;

        Function<Function<Integer, Integer>,
                Function<Integer, Integer>> f1 = compose.apply(square);
        Function<Integer, Integer> f2 = f1.apply(triple);
        System.out.println(f2.apply(2));
    }

    static void staticMethodTest(){
        Function<Integer, Integer> f = s -> s+1;
        Function<Integer, Integer> g = s -> s * 2;
        /**
         * 下面表示在执行F时，先执行G，并且执行F时使用G的输出当作输入。
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         * 相当于：f.g(1)
         */
        System.out.println(f.compose(g).apply(1));
        /**
         * 表示执行F的Apply后使用其返回的值当作输入再执行G的Apply；
         * 相当于以下代码
         * Integer a = f.apply(1);
         * System.out.println(g.apply(a));
         * 相当于 g(f(1)) = g.f(1)
         */
        System.out.println(f.andThen(g).apply(1));
        /**
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(Function.identity().apply("a"));
    }
}
