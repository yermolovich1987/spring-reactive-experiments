package com.dimasco.demo;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BaseReactiveOperationTest {

    @Test
    public void simpleFluxOperations() {
        Flux.just("aaa", "bbb", "ccc")
                .log()
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }

    @Test
    public void simpleBatchSubscriber() {
        Flux.just("aaa", "bbb", "ccc")
                .log()
                .map(String::toUpperCase)
                .subscribe(new Subscriber<String>() {
                    private long count = 0;
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        subscription.request(2);
                    }

                    @Override
                    public void onNext(String s) {
                        count++;
                        if (count>=2) {
                            count = 0;
                            subscription.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
