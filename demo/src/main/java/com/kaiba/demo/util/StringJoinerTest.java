package com.kaiba.demo.util;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * StringJoinerTest
 * Created by luliru on 2019-06-01.
 */
public class StringJoinerTest {

    public static void main(String[] args){
        target();
    }

    public static void basic(){
        StringJoiner joiner = new StringJoiner(":");
        joiner.add("b");
        joiner.add("c");
        System.out.println(joiner.toString());  //b:c

        StringJoiner sj1 = new StringJoiner(":","[","]");
        sj1.add("a").add("b").add("c");
        System.out.println(sj1.toString()); //[a:b:c]
    }

    public static void target(){
        List<String> list = ImmutableList.of("a","b","c");
        String result = list.stream().collect(Collectors.joining(":","[","]"));
        System.out.println(result); //[a:b:c]
    }
}
