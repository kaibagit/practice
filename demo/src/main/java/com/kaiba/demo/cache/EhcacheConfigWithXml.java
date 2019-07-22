package com.kaiba.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

/**
 * EhcacheConfigWithXml
 * Created by luliru on 2019-07-22.
 */
@Slf4j
public class EhcacheConfigWithXml {

    public static void main(String[] args){
        URL myUrl = EhcacheConfigWithXml.class.getResource("/ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();

        Cache<String, String> foo = myCacheManager.getCache("foo",String.class,String.class);
        foo.put("1","zhangsan");
        foo.put("2","lisi");
        log.info("foo,姓名：{}",foo.get("1"));

        Cache<Number, String> bar = myCacheManager.getCache("bar",Number.class,String.class);
        bar.put(1.1,"zhangsan");
        bar.put(2.2,"lisi");
        log.info("bar,姓名：{}",bar.get(1.1));

        Cache<Long, String> simpleCache = myCacheManager.getCache("simpleCache",Long.class,String.class);
        simpleCache.put(1L,"zhangsan");
        simpleCache.put(2L,"lisi");
        log.info("simpleCache,姓名：{}",simpleCache.get(1L));

        myCacheManager.close();
    }
}
