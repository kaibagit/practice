package com.kaiba.demo.jvm.gc.ref;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;

@Slf4j
public class MyBean {

    private byte[] data = new byte[1024*1024];

    private int i;

//    public Reference ref;

    public MyBean(int i){
        this.i = i;
    }

    public void finalize(){
        log.info("{}:{} finalize...",i,this);
    }
}
