package com.kaiba.demo.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * Created by luliru on 2017/3/20.
 */
public class ExceptionTest {

    public static void main(String[] args){
        NullPointerException e = new NullPointerException("This is a demo");
        String json = JSON.toJSONString(e);
        System.out.println(json);
        Exception e2 = JSON.parseObject(json,Exception.class);
        System.out.println(e2.toString());
    }
}
