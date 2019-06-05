package me.luliru.practice.zipkin.controller;

/**
 * ZipkinBraveController4
 * Created by luliru on 2019-06-03.
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("service的API接口")
@RestController
@RequestMapping("/service4")
public class ZipkinBraveController4 {

    @ApiOperation("trace第四步")
    @RequestMapping("/test")
    public String service1() throws Exception {
        Thread.sleep(300);
        return "service4";
    }

}