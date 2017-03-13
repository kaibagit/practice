package com.kaiba.demo.guice.bean;

import com.google.inject.Inject;

/**
 * Created by luliru on 2017/3/13.
 */
public class DemoServiceImplB implements DemoService {

    private Storage storage;

    @Inject
    public DemoServiceImplB(@BBindingAnnotation Storage storage){
        this.storage = storage;
    }

    @Override
    public String sayHello(String name) {
        return "DemoServiceImplB=>"+storage.storeText(name);
    }
}
