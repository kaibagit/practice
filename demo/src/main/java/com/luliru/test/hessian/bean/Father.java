package com.luliru.test.hessian.bean;

import java.io.Serializable;

/**
 * Created by luliru on 2017/7/18.
 */
public class Father implements Serializable{

    private String name = "father";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
