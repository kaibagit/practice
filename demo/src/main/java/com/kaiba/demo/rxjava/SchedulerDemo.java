package com.kaiba.demo.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by luliru on 2017/2/17.
 */
public class SchedulerDemo {

    public static void main(String[] args) throws InterruptedException {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread()+":"+s);
            }

            @Override
            public void onCompleted() {
                System.out.println(Thread.currentThread()+":"+"completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(Thread.currentThread()+":"+"error");
            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread()+":"+"call");
                subscriber.onNext("Hello");
                subscriber.onCompleted();
            }
        });

        //必须链式subscribe，如果先subscribeOn和observeOn，然后再subscribe，则不起作用
        observable.subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(Schedulers.newThread()).subscribe(observer);

        System.out.println(Thread.currentThread()+":"+"main");
        Thread.sleep(5000L);
    }
}
