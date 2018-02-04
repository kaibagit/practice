package com.kaiba.demo.rxjava.v2;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luliru on 2018/2/1.
 */
public class ConcatDemo {

    private static final Logger log = LoggerFactory.getLogger(ConcatDemo.class);

    public static void main(String[] args){
        Observable.concat(Observable.just(1,2,3), Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        log.info("accept:{}",integer);
                    }
                });
    }
}
