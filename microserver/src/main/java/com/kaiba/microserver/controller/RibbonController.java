package com.kaiba.microserver.controller;

import com.kaiba.microserver.service.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by luliru on 2016/8/29.
 */
@RestController
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ComputeService computeService;

    @RequestMapping(value = "ribbon/add", method = RequestMethod.GET)
    public String add() {
        return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20", String.class).getBody();
    }

    @RequestMapping(value = "ribbon/add2", method = RequestMethod.GET)
    public String add2(){
        return computeService.addService();
    }

}