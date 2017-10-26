package com.kaiba.demo.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by luliru on 2017/10/10.
 */
public class RedisTemplateDemo {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:redis/spring/redisTemplateDemo.xml"});
        RedisTemplate template = ctx.getBean("redisTemplate",RedisTemplate.class);
        template.opsForValue().set("hello","world");
        System.out.println(template.opsForValue().get("hello"));

        StringRedisTemplate stringRedisTemplate = ctx.getBean(StringRedisTemplate.class);
        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
    }
}
