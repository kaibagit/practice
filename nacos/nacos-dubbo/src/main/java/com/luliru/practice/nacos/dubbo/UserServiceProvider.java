package com.luliru.practice.nacos.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class UserServiceProvider {

    private static Logger logger = LoggerFactory.getLogger(UserServiceProvider.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:provider.xml");
        logger.info("start log");
        System.out.println("started");
        System.in.read();
    }

}