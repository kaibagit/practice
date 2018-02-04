package com.kaiba.demo.rxjava.v2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/1/29.
 */
public class FirstDemo {

    private static final Logger log = LoggerFactory.getLogger(FirstDemo.class);

    public static void main(String[] args){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                log.info("observable:1");
                e.onNext(2);
                log.info("observable:2");
                e.onNext(3);
                log.info("observable:3");
                e.onComplete();
                log.info("observable:complete");
                e.onNext(4);
                log.info("observable:4");
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                log.info("observer:{}",integer);
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                log.info("observer:complete");
            }
        };

        observable.subscribe(observer);
    }
}
