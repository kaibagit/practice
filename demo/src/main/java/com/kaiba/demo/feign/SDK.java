package com.kaiba.demo.feign;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * Created by luliru on 2018/11/8.
 */
public class SDK {

    public static void main(String[] args) {
        FeignClient feignClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClient.class, "http://localhost");
        feignClient.login("root", "123456");
    }
}