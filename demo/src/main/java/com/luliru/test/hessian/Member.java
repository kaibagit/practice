package com.luliru.test.hessian;

import java.io.Serializable;

/**
 * Created by kaiba on 2016/6/21.
 */
public class Member implements Serializable {

    private Long id;
    private String name;
    private boolean sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
