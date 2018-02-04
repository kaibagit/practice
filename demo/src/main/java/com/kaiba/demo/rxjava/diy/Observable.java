package com.kaiba.demo.rxjava.diy;

import com.kaiba.demo.rxjava.diy.map.MapOnSubscribe;
import com.kaiba.demo.rxjava.diy.scheduler.Scheduler;

/**
 * Created by luliru on 2018/2/1.
 */
public class Observable<T> {

    final OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }

    public interface OnSubscribe<T> {
        void call(Subscriber<? super T> subscriber);
    }


//    public <R> Observable<R> map(Function<? super T, ? extends R> function) {
//        return create(new OnSubscribe<R>() { // 生成一个桥接的Observable和 OnSubscribe
//            @Override
//            public void call(Subscriber<? super R> subscriber) {
//                Observable.this.subscribe(new Subscriber<T>() { // 订阅上层的Observable
//                    @Override
//                    public void onCompleted() {
//                        subscriber.onCompleted();
//                    }
//                    @Override
//                    public void onError(Throwable t) {
//                        subscriber.onError(t);
//                    }
//                    @Override
//                    public void onNext(T var1) {
//                        // 将上层的onSubscribe发送过来的Event，通过转换和处理，转发给目标的subscriber
//                        subscriber.onNext(function.apply(var1));
//                    }
//                });
//            }
//        });
//    }
    public <R> Observable<R> map(Function<? super T, ? extends R> function) {
        return create(new MapOnSubscribe<T, R>(this, function));
    }
    public interface Function<T, R> {
        R apply(T from);
    }



    public Observable<T> subscribeOn(Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onStart();
                // 将事件的生产切换到新的线程。
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onStart();
                Scheduler.Worker worker = scheduler.createWorker();
                Observable.this.onSubscribe.call(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onCompleted();
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable t) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onError(t);
                            }
                        });
                    }
                    @Override
                    public void onNext(T var1) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(var1);
                            }
                        });
                    }
                });
            }
        });
    }
}