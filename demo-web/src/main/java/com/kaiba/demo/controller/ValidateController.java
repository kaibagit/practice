package com.kaiba.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by luliru on 2017/5/16.
 */
@Validated
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

    //不加BindingResult参数，抛出BindException,返回400
    @ResponseBody
    @RequestMapping("user2")
    public void user2(@Valid User user){
        System.out.println(user);
    }

    //抛出javax.validation.ConstraintViolationException
    @ResponseBody
    @RequestMapping("user3")
    public void user3(User user,@NotNull String code){
        System.out.println(user);
        System.out.println("code="+code);
    }

    @ResponseBody
    @RequestMapping("user4")
    public String user4(@NotNull User user,@NotNull String code){
        System.out.println(user);
        System.out.println("code="+code);
        return "user4";
    }
}
