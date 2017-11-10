package com;

import com.kaiba.practice.provider.CacheProvider;
import com.luna.demo.service.BizException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by luliru on 2017/11/10.
 */
public class CacheConsumer {

    private static Logger logger = LoggerFactory.getLogger(CacheConsumer.class);

    public static void main(String[] args) throws TException, BizException, IOException, InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:cache.xml");
        CacheProvider cacheService = ctx.getBean(CacheProvider.class);

        // 测试缓存生效，多次调用返回同样的结果。(服务器端自增长返回值)
        for (int i = 0; i < 5; i ++) {
            String result = cacheService.findCache("0");
            System.out.println("OK: " + result);
            Thread.sleep(6000);
        }

        // LRU的缺省cache.size为1000
        for (int n = 0; n <= 1000; n ++) {
            String result = cacheService.findCache(String.valueOf(n));
            System.out.println("OK: " + result);
        }

        // 测试LRU有移除最开始的一个缓存项
        String result = cacheService.findCache("0"); //request: 0, response: 1001
        System.out.println("OK: " + result);
    }
}
