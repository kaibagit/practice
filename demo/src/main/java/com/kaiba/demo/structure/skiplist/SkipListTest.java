package com.kaiba.demo.structure.skiplist;

/**
 * Created by kaiba on 2017/5/1.
 */
public class SkipListTest {
    public static void main(String[] args) {
        SkipList S = new SkipList();

        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("ABC", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        //System.out.println("======");

        S.put("DEF", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("KLM", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("HIJ", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("GHJ", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.put("AAA", 123);
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        System.out.println("开始remove");
        System.out.println("------");

        S.remove("ABC");
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        //System.out.println("======");

        S.remove("DEF");
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.remove("KLM");
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.remove("HIJ");
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.remove("GHJ");
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");

        S.remove("AAA");
        S.printHorizontal();
        System.out.println("------");
        // S.printVertical();
        // System.out.println("======");
    }
}
