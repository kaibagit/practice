package com.kaiba.demo.javalang;

/**
 * Created by luliru on 2017/5/4.
 */
public class FinallyTest {

    public static void main(String[] args){
        doSomething();
    }

    private static String doSomething(){
        try{
            System.out.println("do something.");
            throw new RuntimeException("error");
        }finally {
            System.out.println("process finally code.");
        }
    }
}
