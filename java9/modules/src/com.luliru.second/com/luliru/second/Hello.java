package com.luliru.second;

import com.luliru.first.*;

public class Hello {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setName("luliru");
        System.out.println(user);
    }
}
