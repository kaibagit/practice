package com.kaiba.demo.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRUCacheV2
 * Created by luliru on 2019-07-22.
 */
public class FIFOCacheV2<K, V> extends LinkedHashMap<K, V> {

    private final int MAX_CACHE_SIZE;

    public FIFOCacheV2(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f);
        MAX_CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }

    public static void main(String[] args){
        FIFOCacheV2<String,String> cache = new FIFOCacheV2<>(3);
        cache.put("a","a");
        cache.put("b","b");
        cache.put("c","c");
        cache.get("a");
        cache.put("d","d");
        System.out.println(cache.toString());
    }
}
