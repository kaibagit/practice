package me.luliru.parctice.algorithm.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指Offer59_II_队列的最大值
 * Created by luliru on 2021/3/15.
 */
public class 剑指Offer59_II_队列的最大值 {
}

class MaxQueue {

    private Queue<Integer> dataQueue = new LinkedList<>();

    // 单调递减双端队列，头部就是最大值
    private Deque<Integer> maxDeque = new LinkedList<>();

    public MaxQueue() {

    }

    public int max_value() {
        if (maxDeque.isEmpty()) {
            return -1;
        }
        return maxDeque.peekFirst();
    }

    public void push_back(int value) {
        dataQueue.offer(value);
        while (!maxDeque.isEmpty() && value > maxDeque.peekLast()) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(value);
    }

    public int pop_front() {
        if (dataQueue.isEmpty()) {
            return -1;
        }

        int val = dataQueue.poll();
        if (maxDeque.peekFirst() == val) {
            maxDeque.pollFirst();
        }
        return val;
    }
}