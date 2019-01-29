package com.luliru.practice.nacos.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2019/1/29.
 */
public class Consumer {

    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:customer.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        while (true){
            try{
                System.out.println(userService.hello("1"));
            }catch (Exception e){
                e.printStackTrace();
            }
            Thread.sleep(5000L);
        }
    }
}