package com.kaiba.demo.jmx.beans;

/**
 * Created by luliru on 2017/3/8.
 */
public class Hello {//don't need implement interface

    private String name;

    public String getName() {
        return name;
    }

    public void printHello() {
        System.out.println("Hello World Model Bean......" + name);
    }

    public void printHello(String name) {
        System.out.println("Hello World Model Bean......." + name);
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("My value is set to " + name);
    }
}