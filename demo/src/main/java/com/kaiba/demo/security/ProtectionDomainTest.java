package com.kaiba.demo.security;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Created by luliru on 2018/8/10.
 */
public class ProtectionDomainTest {

    public static void main(String[] args){
//        defaultProtectionDomain();
        test();
    }

    static void defaultProtectionDomain(){
        Class clazz = String.class;
        ProtectionDomain protectionDomain = clazz.getProtectionDomain();
        System.out.println(protectionDomain);
    }

    static void test(){
        ProtectionDomain protectionDomain = ProtectionDomainTest.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL locationURL = codeSource.getLocation();
        System.out.println("ProtectionDomain:"+protectionDomain);
        System.out.println("CodeSource:"+codeSource);
        System.out.println("URL:"+locationURL);
    }
}
