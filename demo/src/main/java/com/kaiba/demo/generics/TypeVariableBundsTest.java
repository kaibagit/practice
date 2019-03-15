package com.kaiba.demo.generics;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by luliru on 2019/3/14.
 */
public class TypeVariableBundsTest<T extends Number & Serializable & Comparable> {

    private T t;

    public static void main(String[] args) throws NoSuchFieldException {
        Field field = TypeVariableBundsTest.class.getDeclaredField("t");
        TypeVariable typeVariable = (TypeVariable) field.getGenericType();
        // 获取类型变量t的上边界
        Type[] types = typeVariable.getBounds();
        for(Type type : types){
            System.out.println(type);
            //class java.lang.Number
            //interface java.io.Serializable
            //interface java.lang.Comparable
        }
    }
}
