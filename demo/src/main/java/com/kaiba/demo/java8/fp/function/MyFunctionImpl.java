package com.kaiba.demo.java8.fp.function;

public class MyFunctionImpl<Integer> implements MyFunction {

    private int a;

    @Override
    public Object apply(Object o) {
        a++;
        return null;
    }
}
