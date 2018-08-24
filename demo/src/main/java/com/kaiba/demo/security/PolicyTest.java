package com.kaiba.demo.security;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by luliru on 2018/8/22.
 */
public class PolicyTest {

    public static void main(String[] args){
        FileWriter writer;
        try {
            writer = new FileWriter("/Users/luliru/IdeaProjects/test.txt");
            writer.write("hello1");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
