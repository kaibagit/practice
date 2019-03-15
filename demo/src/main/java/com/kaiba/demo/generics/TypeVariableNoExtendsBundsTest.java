package com.kaiba.demo.generics;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by luliru on 2019/3/14.
 */
public class TypeVariableNoExtendsBundsTest<T> {

    private T t;

    public static void main(String[] args) throws NoSuchFieldException {
        testGetName();
    }

    private static void getGetBounds() throws NoSuchFieldException {
        Field field = TypeVariableNoExtendsBundsTest.class.getDeclaredField("t");
        TypeVariable typeVariable = (TypeVariable) field.getGenericType();
        Type[] types = typeVariable.getBounds();
        for(Type type : types){
            System.out.println(type);   //class java.lang.Object
        }
    }

    private static void testGetGenericDeclaration() throws NoSuchFieldException {
        Field field = TypeVariableNoExtendsBundsTest.class.getDeclaredField("t");
        TypeVariable typeVariable = (TypeVariable) field.getGenericType();
        //获取申明该类型变量（T）的实体
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        System.out.println(genericDeclaration); //class com.kaiba.demo.generics.TypeVariableNoExtendsBundsTest
    }

    private static void testGetName() throws NoSuchFieldException {
        Field field = TypeVariableNoExtendsBundsTest.class.getDeclaredField("t");
        TypeVariable typeVariable = (TypeVariable) field.getGenericType();
        // 获取类型变量在源码中定义的名称
        String name = typeVariable.getName();
        System.out.println(name);   //T
    }
}
