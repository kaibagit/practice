package com.kaiba.demo.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Created by luliru on 2017/4/12.
 */
//@Aspect
@Component
public class LogInterceptor2 {

    public void before(){
        System.out.println("method start...");
    }
}