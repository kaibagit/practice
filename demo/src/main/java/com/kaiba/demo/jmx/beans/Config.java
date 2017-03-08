package com.kaiba.demo.jmx.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luliru on 2017/3/7.
 */
public class Config implements ConfigMBean {

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public List<String> summary() {
        List<String> list = new ArrayList<>();
        list.add("name=demo");
        list.add("env=dev");
        return list;
    }

    @Override
    public void set(String name) {
        System.out.println("set:"+name);
    }
}
