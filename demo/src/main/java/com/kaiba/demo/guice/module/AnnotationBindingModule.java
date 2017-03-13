package com.kaiba.demo.guice.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.kaiba.demo.guice.bean.*;

/**
 * Created by luliru on 2017/3/13.
 */
public class AnnotationBindingModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Storage.class).to(FileStorage.class);
        binder.bind(Storage.class).annotatedWith(BBindingAnnotation.class).to(DatabaseStorage.class);
        binder.bind(DemoService.class).to(DemoServiceImplB.class);
    }
}
