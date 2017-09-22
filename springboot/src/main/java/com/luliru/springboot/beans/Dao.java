package com.luliru.springboot.beans;

import org.springframework.stereotype.Component;

/**
 * Created by luliru on 2017/9/22.
 */
@Component
public class Dao {

    private Object obj;

    public void insert(Object obj){
        this.obj = obj;
    }

    public Object get(){
        return obj;
    }
}
