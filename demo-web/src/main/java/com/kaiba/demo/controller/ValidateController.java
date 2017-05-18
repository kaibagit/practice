package com.kaiba.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by luliru on 2017/5/16.
 */
@Controller
@RequestMapping("validate")
public class ValidateController {

    @ResponseBody
    @RequestMapping("user")
    public void user(@Valid User user, BindingResult result){
        System.out.println(result.hasErrors());
        for(ObjectError error : result.getAllErrors()){
            System.out.println(error);
        }
    }
}
