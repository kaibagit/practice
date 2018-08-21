package com.kaiba.demo.javalang.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/8/20.
 */
public class ToBeLoadBean {

    private static final Logger log = LoggerFactory.getLogger(ToBeLoadBean.class);

    static{
        log.info("ToBeLoadBean static init");
    }

    public void hello(){
        log.info("hello");
    }
}