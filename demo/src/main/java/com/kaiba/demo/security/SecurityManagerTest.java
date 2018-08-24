package com.kaiba.demo.security;

import java.lang.reflect.Method;

/**
 * Created by luliru on 2018/8/10.
 */
public class SecurityManagerTest {

    public static void main(String[] args){
        defualtManager();
    }

    static void defualtManager(){
        SecurityManager security = System.getSecurityManager();
        System.out.println(security);
    }

    static void test(){
        System.out.println(Method.class.getClassLoader());
        Exception exception = null;
        try{
            error();
        }catch (Exception e){
            exception = e;
        }

        MySecurityManager mgr = new MySecurityManager();
        Class[] classes = mgr.getClassContext();
        for(Class clazz : classes){
            System.out.println(clazz);
        }
        System.out.println("====================");
        for(StackTraceElement element : exception.getStackTrace()){
            System.out.println(element);
        }
    }

    static void error(){
        throw new RuntimeException("未知异常");
    }

    static class MySecurityManager extends SecurityManager{

        public Class[] getClassContext(){
            return super.getClassContext();
        }
    }
}
