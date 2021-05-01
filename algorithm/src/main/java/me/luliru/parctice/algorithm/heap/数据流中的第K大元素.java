package me.luliru.parctice.algorithm.heap;

import java.util.PriorityQueue;

/**
 * 数据流中的第K大元素
 * Created by luliru on 2021/2/10.
 */
public class 数据流中的第K大元素 {
}

class KthLargest {

    private int k;

    private PriorityQueue<Integer> queue;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>(k);     // Java默认小根堆
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.offer(val);
        } else if (queue.peek() < val) {
            queue.offer(val);
            queue.poll();
        }
        return queue.peek();
    }
}
