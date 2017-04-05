package com.kaiba.demo.javautil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap是HashMap的一个子类，它保留插入的顺序，如果需要输出的顺序和输入时的相同
 * Created by luliru on 2017/4/5.
 */
public class LinkedHashMapDemo {

    public static void main(String[] args){
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for(int i=20;i>0;i--){
            map.put(i,i);
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey());
        }
        System.out.println("over");
    }
}
