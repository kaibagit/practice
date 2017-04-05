package com.kaiba.demo.javautil;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * TreeMap的实现是红黑树算法的实现
 * Created by luliru on 2017/4/5.
 */
public class TreeMapDemo {

    public static void main(String[] args){
        Map<Integer,Integer> map = new TreeMap<>();
        Random random = new Random();
        for(int i=0;i<20;i++){
            int v = random.nextInt(100);
            map.put(v,v);
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey());
        }
        System.out.println("over");
    }
}
