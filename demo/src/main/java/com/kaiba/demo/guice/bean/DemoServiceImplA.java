package com.kaiba.demo.guice.bean;

import com.google.inject.Inject;

/**
 * Created by luliru on 2017/3/13.
 */
public class DemoServiceImplA implements DemoService {

    private Storage storage;

    @Inject
    public DemoServiceImplA(Storage storage){
        this.storage = storage;
    }

    @Override
    public String sayHello(String name) {
        return "DemoServiceImplA=>"+storage.storeText(name);
    }
}
