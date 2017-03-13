package com.kaiba.demo.guice.bean;

/**
 * Created by luliru on 2017/3/13.
 */
public class DatabaseStorage implements Storage {

    @Override
    public String storeText(String content) {
        return "DatabaseStorage:"+content;
    }
}
