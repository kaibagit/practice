package com.kaiba.demo.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kaiba.demo.guice.bean.DemoService;
import com.kaiba.demo.guice.bean.Storage;
import com.kaiba.demo.guice.module.AnnotationBindingModule;
import com.kaiba.demo.guice.module.BindingModule;
import com.kaiba.demo.guice.module.NameBindingModule;

/**
 * Created by luliru on 2017/3/13.
 */
public class GuiceDemo {

    public static void main(String[] args){
        namedTest();
    }

    private static void firstDemo(){
        Injector injector = Guice.createInjector(new BindingModule());
        Storage storage = injector.getInstance(Storage.class);
        System.out.println(storage.storeText("hello"));
        DemoService demoService = injector.getInstance(DemoService.class);
        System.out.println(demoService.sayHello("luliru"));
    }

    private static void annotaionTest(){
        Injector injector = Guice.createInjector(new AnnotationBindingModule());
        Storage storage = injector.getInstance(Storage.class);
        System.out.println(storage.storeText("hello"));
        DemoService demoService = injector.getInstance(DemoService.class);
        System.out.println(demoService.sayHello("luliru"));
    }

    private static void namedTest(){
        Injector injector = Guice.createInjector(new NameBindingModule());
        Storage storage = injector.getInstance(Storage.class);
        System.out.println(storage.storeText("hello"));
        DemoService demoService = injector.getInstance(DemoService.class);
        System.out.println(demoService.sayHello("luliru"));
    }
}
