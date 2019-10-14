package com.kaiba.demo.java8.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalTimeTest
 * Created by luliru on 2019-10-14.
 */
public class LocalTimeTest {

    public static void main(String[] args){
        LocalTime time = LocalTime.now();
        time = LocalTime.of(10,1,59);

        // 把LocalTime实例按照格式“HHmmss”转换为时间字符串
        DateTimeFormatter timeFormatSimple = DateTimeFormatter.ofPattern("HH:mm:ss");
        String strTimeSimple = time.format(timeFormatSimple);
        strTimeSimple = time.toString();

        // 将时间字符串转化为LocalTime对象
        time = LocalTime.parse("10:01:59");
    }
}
