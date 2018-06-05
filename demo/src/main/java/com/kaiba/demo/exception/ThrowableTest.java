package com.kaiba.demo.exception;

/**
 * Created by luliru on 2018/6/3.
 */
public class ThrowableTest {

    public static void main(String[] args) throws InterruptedException {
        new ErrorTask().start();
        new ExceptionTask().start();

        Thread.sleep(5000L);
        System.out.println("Main线程结束");
    }

    static class ErrorTask extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new Error("重大错误");
        }
    }

    static class ExceptionTask extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new RuntimeException("exception");
        }
    }
}
