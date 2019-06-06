package com.kaiba.demo.j2cache;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

/**
 * J2cacheDemo
 * Created by luliru on 2019-06-06.
 */
public class J2cacheDemo {

    public static void main(String[] args){
        reboot();
    }

    public static void init(){
        CacheChannel cache = J2Cache.getChannel();
        cache.set("default", "1", "Hello J2Cache");
        System.out.println(cache.get("default", "1"));
        cache.close();
    }

    public static void reboot() {
        CacheChannel cache = J2Cache.getChannel();
        System.out.println(cache.get("default", "1"));
        cache.close();
    }

    public static void whole(){
        CacheChannel cache = J2Cache.getChannel();

        //缓存操作
        cache.set("default", "1", "Hello J2Cache");
        System.out.println(cache.get("default", "1"));
        cache.evict("default", "1");
        System.out.println(cache.get("default", "1"));

        cache.close();
    }
}
