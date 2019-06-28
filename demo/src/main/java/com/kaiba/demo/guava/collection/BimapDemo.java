package com.kaiba.demo.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * BimapDemo
 * Created by luliru on 2019-06-28.
 */
public class BimapDemo {

    public static void main(String[] args){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        logfileMap.put(4,"d.log");
        // 在使用BiMap时，会要求Value的唯一性。
        // 如果我们确实需要插入重复的value值，那可以选择forcePut方法。但是我们需要注意的是前面的key也会被覆盖了。
        logfileMap.forcePut(5,"d.log");
        System.out.println("logfileMap:"+logfileMap);
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);
    }
}
