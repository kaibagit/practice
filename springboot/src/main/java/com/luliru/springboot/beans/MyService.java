package com.luliru.springboot.beans;

/**
 * Created by luliru on 2016/12/29.
 */
public class MyService {

    public String hello(){
        System.out.println("hello"+this);
        return "world";
    }
}
