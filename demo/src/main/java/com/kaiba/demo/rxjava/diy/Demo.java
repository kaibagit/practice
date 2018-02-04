package com.kaiba.demo.rxjava.diy;

import com.kaiba.demo.rxjava.diy.scheduler.Schedulers;

/**
 * Created by luliru on 2018/2/1.
 */
public class Demo {

    public static void main(String[] args){
        testScheduler();
    }

    private static void firstTest(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable t) {

            }
            @Override
            public void onNext(Integer var1) {
                System.out.println(var1);
            }
        });
    }

    private static void testMap(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                }
            }
        }).map(new Observable.Function<Integer, String>() {
            @Override
            public String apply(Integer from) {
                return "maping " + from;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String var1) {
                System.out.println(var1);
            }
            @Override
            public void onCompleted() {}
            @Override
            public void onError(Throwable t) {}
        });
    }

    private static void testScheduler(){
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                System.out.println("OnSubscribe@ " + Thread.currentThread().getName()); //new Thread
//                subscriber.onNext(1);
//            }
//        })
//        .subscribeOn(Schedulers.io())
//        .subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onNext(Integer var1) {
//                System.out.println("Subscriber@ " + Thread.currentThread().getName()); // new Thread
//                System.out.println(var1);
//            }
//        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName()); // main
                subscriber.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onNext(Integer var1) {
                System.out.println("Subscriber@ " + Thread.currentThread().getName()); // new Thread
                System.out.println(var1);
            }
        });
    }
}
