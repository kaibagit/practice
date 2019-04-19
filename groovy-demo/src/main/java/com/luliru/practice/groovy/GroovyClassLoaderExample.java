package com.luliru.practice.groovy;

import groovy.lang.GroovyClassLoader;

public class GroovyClassLoaderExample {

    public static void main(String[] args){
        ClassLoader cl = new GroovyClassLoaderExample().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
    }
}
