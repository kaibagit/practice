package com.kaiba.demo.rxjava.diy.map;

import com.kaiba.demo.rxjava.diy.Observable;
import com.kaiba.demo.rxjava.diy.Subscriber;

/**
 * Created by luliru on 2018/2/4.
 */
public class MapSubscriber<T, R> extends Subscriber<R> {
    final Subscriber<? super T> actual;
    final Observable.Function<? super R, ? extends T> function;
    public MapSubscriber(Subscriber<? super T> actual, Observable.Function<? super R, ? extends T> function) {
        this.actual = actual;
        this.function = function;
    }
    @Override
    public void onCompleted() {
        actual.onCompleted();
    }
    @Override
    public void onError(Throwable t) {
        actual.onError(t);
    }
    @Override
    public void onNext(R var1) {
        actual.onNext(function.apply(var1));
    }
}