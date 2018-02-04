package com.kaiba.demo.rxjava.v2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/2/1.
 */
public class MapDemo {

    private static final Logger log = LoggerFactory.getLogger(MapDemo.class);

    public static void main(String[] args){
        Consumer consumer = new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                log.info("accept:{}",s);
            }
        };


        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        });

        Observable<String> map = observable.map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "This is result " + integer;
            }
        });

        map.subscribe(consumer);
    }

}
