package com.kaiba.demo.rxjava.diy.map;

import com.kaiba.demo.rxjava.diy.Observable;
import com.kaiba.demo.rxjava.diy.Subscriber;

/**
 * Created by luliru on 2018/2/4.
 */
public class MapOnSubscribe<T, R> implements Observable.OnSubscribe<R> {
    final Observable<T> source;
    final Observable.Function<? super T, ? extends R> function;
    public MapOnSubscribe(Observable<T> source, Observable.Function<? super T, ? extends R> function) {
        this.source = source;
        this.function = function;
    }
    @Override
    public void call(Subscriber<? super R> subscriber) {
        source.subscribe(new MapSubscriber<R, T>(subscriber, function));
    }
}
