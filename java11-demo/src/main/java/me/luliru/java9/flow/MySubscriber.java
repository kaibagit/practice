package me.luliru.java9.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class MySubscriber<T> implements Flow.Subscriber<T> {

    private static Logger log = LoggerFactory.getLogger(MySubscriber.class);

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1); //这里要使用Long.MAX_VALUE就会被认为获取无穷的数据。
    }

    @Override
    public void onNext(T item) {
        log.info("Got : " + item);
        subscription.request(1); //这里也可以使用Long.MAX_VALUE
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error",throwable);
    }

    @Override
    public void onComplete() {
        log.info("Done");
    }

    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        //Register Subscriber
        MySubscriber<String> subscriber = new MySubscriber<>();
        publisher.subscribe(subscriber);

        //Publish items
        log.info("Publishing Items...");
        String[] items = {"1", "x", "2", "x", "3", "x"};
        Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
        publisher.close();
        Thread.sleep(20000);
    }
}
