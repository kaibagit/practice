package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by luliru on 2017/11/10.
 */
public class CacheMain {

    private static Logger logger = LoggerFactory.getLogger(CacheMain.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:cache.xml");
        logger.info("start log");
        System.out.println("started");
        System.in.read();
    }
}
