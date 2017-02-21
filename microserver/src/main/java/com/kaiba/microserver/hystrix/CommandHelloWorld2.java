package com.kaiba.microserver.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * Created by luliru on 2017/2/16.
 */
public class CommandHelloWorld2 extends HystrixCommand<String> {

    protected CommandHelloWorld2() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected String run() throws Exception {
        return "hello world !";
    }

    public static void main(String[] args){
        String s = new CommandHelloWorld2().execute();
        Future<String> future = new CommandHelloWorld2().queue();
        Observable<String> observable = new CommandHelloWorld2().observe();


    }
}
