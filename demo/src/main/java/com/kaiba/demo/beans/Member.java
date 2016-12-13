package com.kaiba.demo.beans;

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

    public Member(){

    }

    public Member(Long id, String name, boolean sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
