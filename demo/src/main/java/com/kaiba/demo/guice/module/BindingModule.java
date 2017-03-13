package com.kaiba.demo.guice.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.kaiba.demo.guice.bean.DemoService;
import com.kaiba.demo.guice.bean.DemoServiceImplA;
import com.kaiba.demo.guice.bean.FileStorage;
import com.kaiba.demo.guice.bean.Storage;

/**
 * Created by luliru on 2017/3/13.
 */
public class BindingModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Storage.class).to(FileStorage.class);
        binder.bind(DemoService.class).to(DemoServiceImplA.class);
    }
}
