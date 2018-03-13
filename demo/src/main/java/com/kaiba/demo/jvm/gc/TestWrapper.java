package com.kaiba.demo.jvm.gc;

import java.util.Map;
import java.util.Random;
public class TestWrapper {
    // JVM配置：-Xmx10m -XX:+UseParallelGC
    // JVM配置：-Xmx12m -XX:+UseParallelGC
    // JVM配置：-Xmx13m -XX:+UseParallelGC
    // 分别抛出不同的OOM原因
    public static void main(String args[]) throws Exception {
        Map map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }
    }
}
