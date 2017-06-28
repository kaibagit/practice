package com.kaiba.demo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kaiba.demo.beans.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luliru on 2017/6/28.
 */
public class WriteClassNameDemo {

    public static void main(String[] args){
        List list = new ArrayList();

        Member m = new Member();
        m.setId(1L);
        m.setName("luliru");
        m.setSex(true);
        m.setBithday(LocalDate.of(2017,01,12));
        m.setSleepTime(LocalTime.of(10,23,12));
        m.setDateTime(LocalDateTime.now());
        m.setDate(new Date());

        list.add(m);
        list.add(m);

        String json = JSON.toJSONString(list, SerializerFeature.WriteClassName,SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(json);
        list = (List) JSON.parse(json);
        System.out.println(list);
    }
}
