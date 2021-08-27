package com.kaiba.microserver.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

/**
 * Created by luliru on 2017/3/17.
 */
public class ResumeWithFallbackDemo extends HystrixCommand<String> {

    private final String name;

    public ResumeWithFallbackDemo(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        throw new RuntimeException("this command always fails");
    }

//    @Override
//    protected String getFallback() {
//        return "Hello Failure " + name + "!";
//    }

    public static void main(String[] args){
        ResumeWithFallbackDemo command = new ResumeWithFallbackDemo("luliru");
        Observable<String> observable = command.getFallbackObservable();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError:"+throwable);

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:"+s);
            }
        });
        String result = command.execute();
        System.out.println(result);
    }
}
