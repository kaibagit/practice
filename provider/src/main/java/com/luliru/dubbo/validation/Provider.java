package com.luliru.dubbo.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2017/3/17.
 */
public class Provider {

    private static Logger logger = LoggerFactory.getLogger(Provider.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:validation.xml");
        logger.info("start log");
        System.out.println("started");
        while(true){
            Thread.sleep(10);
        }
    }

}
