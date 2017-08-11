package com.luliru;

/**
 * Created by luliru on 2017/8/11.
 */
public class Home {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "Home{" +
                "name='" + name + '\'' +
                '}';
    }
}
