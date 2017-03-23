package com.kaiba.microserver.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luliru on 2016/8/29.
 */
//@RestController
public class RibbonWithHystrixController {

    @Autowired
    private ComputeService computeService;

    @RequestMapping(value = "ribbon/add", method = RequestMethod.GET)
    public String add(){
        return computeService.addService();
    }

}
