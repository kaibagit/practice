package com.kaiba.demo.javalang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/10/30.
 */
public class ExceptionHandlerTest {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerTest.class);

    public static void main(String[] args) throws InterruptedException {

    }

    private static void setUncaughtExceptionHandler() throws InterruptedException {
        Thread thread = new Thread(){
            public void run(){
                log.info("begin");
                boolean fail = true;
                if(fail){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("未知异常");
                }
                log.info("end");
            }
        };
        thread.setUncaughtExceptionHandler(new MyExceptionHandler());
        thread.start();
        Thread.sleep(3000L);
    }

    private static void setDefaultUncaughtExceptionHandler() throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());

        Thread thread = new Thread(){
            public void run(){
                log.info("begin");
                boolean fail = true;
                if(fail){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("未知异常");
                }
                log.info("end");
            }
        };
        thread.start();
        Thread.sleep(3000L);
    }
}

class MyExceptionHandler implements Thread.UncaughtExceptionHandler{

    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("捕获[{}]异常",t.getName(),e);
    }
}
