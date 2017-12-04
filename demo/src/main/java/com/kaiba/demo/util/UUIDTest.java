package com.kaiba.demo.util;

import java.util.UUID;

/**
 * Created by luliru on 2017/12/4.
 */
public class UUIDTest {

    public static void main(String[] args){
        long begin = System.currentTimeMillis();
        int c = 1000000;
        for(int i=0 ;i<c;i++){
            UUID.randomUUID();
        }
        long end = System.currentTimeMillis();
        System.out.println("qps:"+(c*1.0/(end-begin)*1000));    //40w qps
    }
}
