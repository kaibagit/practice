package com.kaiba.demo.fastjson;

import com.alibaba.fastjson.JSON;
import com.kaiba.demo.beans.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by luliru on 2017/3/4.
 */
public class Java8Test {

    public static void main(String[] args){
        Member m = new Member();
        m.setId(1L);
        m.setName("luliru");
        m.setSex(true);
        m.setBithday(LocalDate.of(2017,01,12));
        m.setSleepTime(LocalTime.of(10,23,12));
        m.setDateTime(LocalDateTime.now());
        m.setDate(new Date());

        String json = JSON.toJSONString(m);
        System.out.println(json);
        Member p2 = JSON.parseObject(json, Member.class);
    }
}
