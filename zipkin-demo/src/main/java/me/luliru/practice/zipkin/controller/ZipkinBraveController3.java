package me.luliru.practice.zipkin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ZipkinBraveController3
 * Created by luliru on 2019-06-03.
 */
@Api("service的API接口")
@RestController
@RequestMapping("/service3")
public class ZipkinBraveController3 {

    @ApiOperation("trace第三步")
    @RequestMapping("/test")
    public String service1() throws Exception {
        Thread.sleep(300);
        return "service3";
    }

}