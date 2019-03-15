package com.kaiba.demo.generics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by luliru on 2019/3/14.
 */
public class ParameterizedTypeTest<T> {

    private List<T> list;

    private Set<T> set;

    private Map<String,Integer> map;

    private Map.Entry<String,Integer> mapEntry;

    public static void main(String[] args) throws NoSuchFieldException {
        testGetOwnerType();
    }

    private static void testClassName() throws NoSuchFieldException {
        Field listField = ParameterizedTypeTest.class.getDeclaredField("list");
        Type listType = listField.getGenericType();
        System.out.println(listType.getClass().getName());  //sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl

        Field setField = ParameterizedTypeTest.class.getDeclaredField("set");
        Type setType = setField.getGenericType();
        System.out.println(setType.getClass().getName());   //sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
    }

    private static void testGetActualTypeArguments() throws NoSuchFieldException {
        Field mapField = ParameterizedTypeTest.class.getDeclaredField("map");
        Type mapType = mapField.getGenericType();
        ParameterizedType mapParameterizedType = (ParameterizedType)mapType;    //java.util.Map<java.lang.String, java.lang.Integer>
        Type[] types = mapParameterizedType.getActualTypeArguments();
        System.out.println(types[0]);   //class java.lang.String
        System.out.println(types[1]);   //class java.lang.Integer
    }

    private static void testGetRawType() throws NoSuchFieldException {
        Field mapField = ParameterizedTypeTest.class.getDeclaredField("map");
        Type mapType = mapField.getGenericType();
        ParameterizedType mapParameterizedType = (ParameterizedType)mapType;    //java.util.Map<java.lang.String, java.lang.Integer>
        Type rawType = mapParameterizedType.getRawType();
        System.out.println(rawType);    // interface java.util.Map
    }

    private static void testGetOwnerType() throws NoSuchFieldException {
        Field mapEntryField = ParameterizedTypeTest.class.getDeclaredField("mapEntry");
        Type mapEntryType = mapEntryField.getGenericType();
        ParameterizedType mapEntryParameterizedType = (ParameterizedType) mapEntryType;
        Type ownerType = mapEntryParameterizedType.getOwnerType();
        System.out.println(ownerType);  //interface java.util.Map
    }
}
