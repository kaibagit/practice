package com.kaiba.demo.jmx.beans;

import java.util.List;

/**
 * Created by luliru on 2017/3/7.
 */
public interface ConfigMBean {

    List<String> summary();

    void set(String name);

    void setDate(String date);

    String getDate();
}
