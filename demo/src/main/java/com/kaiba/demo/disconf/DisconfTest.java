package com.kaiba.demo.disconf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by luliru on 2017/5/20.
 */
@Component
public class DisconfTest {

    public JedisConfig getJedisConfig() {
        return jedisConfig;
    }

    @Resource

    private JedisConfig jedisConfig;

    public static void main(String[] args) throws InterruptedException {
        testConfig();
    }

    private static void teistProperties() throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:disconf/application.xml"});
        JedisConfig bean = ctx.getBean(JedisConfig.class);
        while (true){
            System.out.println(bean.toString());
            Thread.sleep(1000L);
        }
    }

    private static void testConfig() throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:disconf/application.xml"});
        AppConfig bean = ctx.getBean(AppConfig.class);
        while (true){
            System.out.println(bean.getWelcomeWords());
            Thread.sleep(1000L);
        }
    }
}
