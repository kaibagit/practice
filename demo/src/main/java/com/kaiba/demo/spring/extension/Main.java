package com.kaiba.demo.spring.extension;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luliru on 2016/10/14.
 */
public class Main {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/extension/spring.xml"});
    }
}
