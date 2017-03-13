package com.kaiba.demo.guice.bean;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by luliru on 2017/3/13.
 */
public class DemoServiceImplC implements DemoService {

    private Storage storage;

    @Inject
    public DemoServiceImplC(@Named("database") Storage storage){
        this.storage = storage;
    }

    @Override
    public String sayHello(String name) {
        return "DemoServiceImplC=>"+storage.storeText(name);
    }
}
