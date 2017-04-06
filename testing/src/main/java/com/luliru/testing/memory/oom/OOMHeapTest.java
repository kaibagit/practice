package com.luliru.testing.memory.oom;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 需要配置JVM参数：-Xms64m -Xmx64m -XX:+HeapDumpOnOutOfMemoryError
 * Created by kaiba on 2017/4/6.
 */
public class OOMHeapTest {
    public static void main(String[] args){
        oom();
    }

    private static void oom(){
        Map<String, Pilot> map = new HashMap<String, Pilot>();
        Object[] array = new Object[1000000];
        for(int i=0; i<1000000; i++){
            String d = new Date().toString();
            Pilot p = new Pilot(d, i);
            map.put(i+"rosen jiang", p);
            array[i]=p;
            System.out.println(i);
        }
    }
}
