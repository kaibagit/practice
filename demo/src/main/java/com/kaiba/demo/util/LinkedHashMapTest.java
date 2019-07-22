package com.kaiba.demo.util;

import java.util.LinkedHashMap;

/**
 * LinkedHashMapTest
 * Created by luliru on 2019-07-19.
 */
public class LinkedHashMapTest {

    public static void main(String[] args){
        LinkedHashMap<String,String> map = new LinkedHashMap<>(16,0.75f,true);
        map.put("d","4");
        map.put("c","3");
        map.put("b","2");
        map.put("a","1");
        map.forEach((key,value) ->{
            System.out.println(key+":"+value);
        });
        System.out.println("=====");
        map.put("c","3");
        map.put("d","4");
        map.forEach((key,value) ->{
            System.out.println(key+":"+value);
        });
    }
}
