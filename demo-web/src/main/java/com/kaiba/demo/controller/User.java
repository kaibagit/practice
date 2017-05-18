package com.kaiba.demo.controller;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by luliru on 2017/5/16.
 */
public class User implements Serializable {
    @NotNull(message = "ID不能为空")
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 5, max = 20, message = "用户名长度必须在5到20之间")
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "用户名必须是字母")
    private String name;

    @NotNull(message = "密码不能为空")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
