package com.kaiba.demo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by luliru on 2017/4/10.
 */
public class AtomicReferenceFieldUpdaterDemo {

    public static void main(String[] args){
        AtomicReferenceFieldUpdater<Cat, String> updater = AtomicReferenceFieldUpdater.newUpdater(Cat.class,
                String.class, "name");
        Cat cat1 = new Cat();
        updater.compareAndSet(cat1, "mycat", "myPet");
        System.out.println(cat1.name); // myPet
        cat1.name = "mycat1";
        updater.compareAndSet(cat1, "mycat", "myPet");  //原name值不是"mycat"时，不予更新
        System.out.println(cat1.name); // mycat1
    }

    static class Cat{
        volatile String name = "mycat";
    }
}
