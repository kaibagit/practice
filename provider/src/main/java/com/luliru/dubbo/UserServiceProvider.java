package com.luliru.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2017/3/17.
 */
public class UserServiceProvider {

    private static Logger logger = LoggerFactory.getLogger(UserServiceProvider.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:provider.xml");
        logger.info("start log");
        System.out.println("started");
        while(true){
            Thread.sleep(10);
        }
    }

}
