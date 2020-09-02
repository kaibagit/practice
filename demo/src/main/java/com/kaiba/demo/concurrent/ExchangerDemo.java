package com.kaiba.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    private final static Logger logger = LoggerFactory.getLogger(ExchangerDemo.class);

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            try {
                logger.info("begin exchange...");
                String resp = exchanger.exchange("hello,this is "+Thread.currentThread());
                logger.info("收到：{}",resp);
                logger.info("finish exchange...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                logger.info("begin exchange...");
                String resp = exchanger.exchange("This is "+Thread.currentThread());
                logger.info("收到：{}",resp);
                logger.info("finish exchange...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
