package com.kaiba.demo.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2016/8/15.
 */
public class ClusterMain {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:quartz.xml");

        while(true){
            Thread.sleep(1000L);
        }
    }
}
