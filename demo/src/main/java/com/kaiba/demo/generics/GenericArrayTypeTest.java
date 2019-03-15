package com.kaiba.demo.generics;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by luliru on 2019/3/14.
 */
public class GenericArrayTypeTest<T> {

    private T[] t;
    private List<String>[] listArray;

    public static void main(String[] args) throws NoSuchFieldException {
        testGetGenericComponentType();
    }

    private static void basic() throws NoSuchFieldException {
        Field listArrayField = GenericArrayTypeTest.class.getDeclaredField("listArray");
        Type listArrayType = listArrayField.getGenericType();
        System.out.println(listArrayType.getClass().getName());     //sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl

        Field tField = GenericArrayTypeTest.class.getDeclaredField("t");
        Type tType = tField.getGenericType();
        System.out.println(tType.getClass().getName());     //sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl
    }

    private static void testGetGenericComponentType() throws NoSuchFieldException {
        Field listArrayField = GenericArrayTypeTest.class.getDeclaredField("listArray");
        Type listArrayType = listArrayField.getGenericType();
        GenericArrayType genericArrayType = (GenericArrayType) listArrayType;
        Type type = genericArrayType.getGenericComponentType();
        System.out.println(type);   //java.util.List<java.lang.String>
    }
}
