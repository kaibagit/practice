package com.kaiba.demo.generics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by luliru on 2019/3/14.
 */
public class TypeVariableTest<T> {

    private List<T> list;

    public static void main(String[] args) throws NoSuchFieldException {
        Field listField = TypeVariableTest.class.getDeclaredField("list");
        Type listType = listField.getGenericType();
        ParameterizedType listParameterizedType = (ParameterizedType) listType;
        Type[] types = listParameterizedType.getActualTypeArguments();
        System.out.println(types[0].getClass().getName());
    }
}
