package com.kaiba.demo.generics;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by luliru on 2019/3/14.
 */
public class ClassTest {

    private ClassTest classTest;

    public static void main(String[] args) throws NoSuchFieldException {
        Field field = ClassTest.class.getDeclaredField("classTest");
        Type type = field.getGenericType();
        System.out.println(type);   //class com.kaiba.demo.generics.ClassTest
    }
}
