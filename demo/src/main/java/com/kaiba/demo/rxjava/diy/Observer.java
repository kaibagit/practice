package com.kaiba.demo.rxjava.diy;

/**
 * Created by luliru on 2018/2/1.
 */
public interface Observer<T> {
    void onCompleted();
    void onError(Throwable t);
    void onNext(T var1);
}
