package com.kaiba.demo.javalang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by luliru on 2017/6/28.
 */
public class GenericityTest {

    public static void main(String[] args){
        Type t = new Base<Map<String, List<String>>>() {
        }.getClass().getGenericSuperclass();
        System.out.println(
            new Base<Map<String, List<String>>>() {}.getClass().getTypeParameters()
        );
        Type targ = ((ParameterizedType) t).getActualTypeArguments()[0];
        System.out.println(t);
        System.out.println(targ);
    }
}
