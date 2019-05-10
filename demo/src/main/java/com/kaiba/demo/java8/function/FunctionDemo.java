package com.kaiba.demo.java8.function;

import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args){
        highOrderFunction();
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
}
