package com.kaiba.demo.javalang;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luliru on 2017/8/12.
 */
public enum Color{
    RED("red"),BLUE("blue"),BLACK("black"),YELLOW("yellow"),GREEN("green");

    private static final Map<String,Color> lookup
            = new HashMap<String,Color>();

    static {
        for(Color s : EnumSet.allOf(Color.class))
            lookup.put(s.getName(), s);
    }

    public static Color get(String name) {
        return lookup.get(name);
    }

    private String name;

    private Color(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
