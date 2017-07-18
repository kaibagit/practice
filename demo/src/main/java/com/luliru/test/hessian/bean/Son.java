package com.luliru.test.hessian.bean;

/**
 * Created by luliru on 2017/7/18.
 */
public class Son extends Father {

    private String name = "son";

//    @Override
    public String getName() {
        return name;
    }

//    @Override
    public void setName(String name) {
        this.name = name;
    }
}
