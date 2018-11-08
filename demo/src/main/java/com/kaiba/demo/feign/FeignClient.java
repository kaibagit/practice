package com.kaiba.demo.feign;

import feign.Param;
import feign.RequestLine;

/**
 * Created by luliru on 2018/11/8.
 */
public interface FeignClient {
    @RequestLine("GET /user/login")
    public String login(@Param("username") String username, @Param("password") String password);
}
