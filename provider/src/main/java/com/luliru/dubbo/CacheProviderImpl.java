package com.luliru.dubbo;

import com.kaiba.practice.provider.CacheProvider;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luliru on 2017/11/10.
 */
@Component
public class CacheProviderImpl implements CacheProvider {

    private final AtomicInteger i = new AtomicInteger();

    @Override
    public String findCache(String id) {
        return "request: " + id + ", response: " + i.getAndIncrement();
    }
}
