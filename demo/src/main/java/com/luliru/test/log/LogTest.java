package com.luliru.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by kaiba on 2016/7/23.
 */
public class LogTest {

    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args){
        MDC.put("traceId", UUID.randomUUID().toString().replace("-",""));
        logger.debug("输出“调试”级别的日志信息");
        logger.info("输出“信息”级别的日志信息");
        logger.warn("输出“警告”级别的日志信息");
        logger.error("输出“错误”级别的日志信息");
        logger.trace("输出“致命错误”级别的日志信息");
    }
}
