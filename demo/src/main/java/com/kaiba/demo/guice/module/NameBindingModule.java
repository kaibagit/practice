package com.kaiba.demo.guice.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.kaiba.demo.guice.bean.*;

/**
 * Created by luliru on 2017/3/13.
 */
public class NameBindingModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Storage.class).to(FileStorage.class);
        binder.bind(Storage.class).annotatedWith(Names.named("database")).to(DatabaseStorage.class);
        binder.bind(DemoService.class).to(DemoServiceImplC.class);
    }
}
