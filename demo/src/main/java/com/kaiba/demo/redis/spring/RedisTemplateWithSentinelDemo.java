package com.kaiba.demo.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by luliru on 2017/10/10.
 */
public class RedisTemplateWithSentinelDemo {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:redis/spring/redisTemplateWithSentinelDemo.xml"});
        RedisTemplate template = ctx.getBean(RedisTemplate.class);
        template.opsForValue().set("hello","world");
        System.out.println(template.opsForValue().get("hello"));
    }
}
