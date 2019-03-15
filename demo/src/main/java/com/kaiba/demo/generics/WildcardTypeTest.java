package com.kaiba.demo.generics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * Created by luliru on 2019/3/15.
 */
public class WildcardTypeTest {

    private List<? extends Number> listNum;

    private List<? super String> listStr;

    public static void main(String[] args) throws NoSuchFieldException {
        getUpperBounds();
    }

    private static void basic() throws NoSuchFieldException {
        Field numField = WildcardTypeTest.class.getDeclaredField("listNum");
        Type numType = numField.getGenericType();
        ParameterizedType numTarameterizedType = (ParameterizedType) numType;
        Type[] numTypes = numTarameterizedType.getActualTypeArguments();
        System.out.println(numTypes[0].getClass()); //class sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
    }

    private static void getUpperBounds() throws NoSuchFieldException {
        Field numField = WildcardTypeTest.class.getDeclaredField("listNum");
        Type numType = numField.getGenericType();
        ParameterizedType numTarameterizedType = (ParameterizedType) numType;
        Type[] numTypes = numTarameterizedType.getActualTypeArguments();
        WildcardType wildcardType = (WildcardType) numTypes[0];
        Type[] types = wildcardType.getUpperBounds();
        System.out.println(types[0]);   // class java.lang.Number
    }

    private static void getLowerBounds() throws NoSuchFieldException {
        Field strField = WildcardTypeTest.class.getDeclaredField("listStr");
        Type strType = strField.getGenericType();
        ParameterizedType strTarameterizedType = (ParameterizedType) strType;
        Type[] strTypes = strTarameterizedType.getActualTypeArguments();
        WildcardType wildcardType = (WildcardType) strTypes[0];
        Type[] types = wildcardType.getLowerBounds();
        System.out.println(types[0]);   // class java.lang.String
    }
}
