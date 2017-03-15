package com.kaiba.demo.concurrent;

import java.util.PriorityQueue;

/**
 * Created by luliru on 2017/3/10.
 */
public class PriorityQueueTest {

    public static void main(String[] args){
        Integer[] arr = {1,34,56,32,456,55,12,46,556,678,896};
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i : arr){
            queue.add(i);
        }
        Integer v = queue.poll();
        while(v != null){
            System.out.println(v);
            v = queue.poll();
        }
    }
}
