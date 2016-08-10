package com.kaiba.demo.jvm.stack;

/**
 * Created by luliru on 2016/8/9.
 */
public class StackTraceTest {

    public static void main(String[] args){
        Member m = Member.findById(1L);
        System.out.println(m);
    }
}
