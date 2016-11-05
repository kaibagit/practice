package com.luliru.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2016/11/5.
 */
public class ThriftProvider {

    private static Logger logger = LoggerFactory.getLogger(ThriftProvider.class);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:thrift_provider.xml");
        logger.info("start log");
        System.out.println("started");
        while(true){
            Thread.sleep(10);
        }
    }

}
