package com.kaiba.demo.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by luliru on 2017/2/20.
 */
public class ConversionDemo {

    public static void main(String[] args){
        flatMap();
    }

    public static void map(){
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onCompleted();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onNext(Integer length) {
                System.out.println(length);
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error:"+e);
            }
        };

        //数据流向：call => map函数 => observer
        observable.map(new Func1<String,Integer>(){
            @Override
            public Integer call(String name) {
                System.out.println("map:"+name+"->"+name.length());
                return name.length();
            }
        }).subscribe(observer);
    }

    public static void flatMap(){
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onCompleted();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onNext(Integer length) {
                System.out.println(length);
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error:"+e);
            }
        };

        //数据流向：call => map函数 => observer
        observable.flatMap(new Func1<String,Observable<Integer>>(){
            @Override
            public Observable<Integer> call(String name) {
                System.out.println("flatmap:"+name);
                return Observable.from(new Integer[]{name.length(),name.length()});
            }
        }).subscribe(observer);
    }
}
