//package com.kaiba.demo.rxjava.v1;
//
//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//
///**
// * Created by luliru on 2017/2/17.
// */
//public class FirstDemo {
//
//    public static void main(String[] args){
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
//
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("error");
//            }
//        };
//
//        observable.subscribe(observer);
//    }
//}
