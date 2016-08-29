package com.kaiba.microserver.controller;

import com.kaiba.microserver.service.ComputeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luliru on 2016/8/29.
 */
@RestController
public class FeignController {

    @Autowired
    ComputeClient computeClient;

    @RequestMapping(value = "/feign/add", method = RequestMethod.GET)
    public Integer add() {
        return computeClient.add(10, 20);
    }
}
