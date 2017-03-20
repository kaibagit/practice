package com.kaiba.demo.gson;

import com.google.gson.Gson;

/**
 * Created by luliru on 2017/3/20.
 */
public class ExceptionTest {

    public static void main(String[] args){
        Gson gson = new Gson();
        NullPointerException e = new NullPointerException("This is a demo");
        String json = gson.toJson(e);
        System.out.println(json);
        Exception e2 = gson.fromJson(json,IllegalArgumentException.class);
        System.out.println(e2.toString());
    }
}
