package me.luliru.parctice.algorithm.slidingwindow;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 剑指Offer59_I滑动窗口的最大值
 * Created by luliru on 2020/10/14.
 */
public class 剑指Offer59_I滑动窗口的最大值 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        new 剑指Offer59_I滑动窗口的最大值().maxSlidingWindow(nums,3);
    }

    /**
     * 暴力穷举
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow_v1(int[] nums, int k) {
        if(nums.length == 0) {
            return new int[]{};
        }
        int limit = nums.length-k;
        int[] res = new int[limit+1];
        for(int i=0;i<=limit;i++){
            int max = nums[i];
            for(int j=1;j<k;j++) {
                max = Math.max(max,nums[i+j]);
            }
            res[i] = max;
        }
        return res;
    }

    /**
     * 优先级队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[]{};
        }

        int limit = nums.length - k + 1;
        int[] res = new int[limit];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (int i = 0; i < nums.length; i++) {
            queue.offer(new int[]{i, nums[i]});
            if (i >= k - 1) {   // 前面只是将元素插入，这里开始才插满滑动窗口，开始输出结果
                // 滑动窗口的有效范围：[i - k + 1, i]
                while (queue.peek()[0] < i - k + 1) {
                    queue.poll();
                }
                // 结果数组坐标：i - k - 1
                res[i - k + 1] = queue.peek()[1];
            }
        }

        return res;
    }

    /**
     * 单调递减滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow_v3(int[] nums, int k) {
        if(nums.length == 0) {
            return new int[]{};
        }
        int limit = nums.length-k;
        int[] res = new int[limit+1];
        LinkedList<Integer> deque = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            int num = nums[i];

            // dequeue保持单调递减，移除比当前元素小的数
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            // 将当前元素插入
            deque.addLast(num);
            // 移除已滑过的元素
            if(i-k>=0 && deque.peekFirst() == nums[i-k]){
                deque.pollFirst();
            }

            // 将dequeue头部元素放入结果数组
            int resIndex = i - k + 1;
            if(resIndex >= 0) {
                res[resIndex] = deque.peekFirst();
            }
        }
        return res;
    }


}
