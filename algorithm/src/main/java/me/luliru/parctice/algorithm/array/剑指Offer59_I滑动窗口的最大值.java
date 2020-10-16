package me.luliru.parctice.algorithm.array;

import java.util.LinkedList;

/**
 * 剑指Offer59_I滑动窗口的最大值
 * Created by luliru on 2020/10/14.
 */
public class 剑指Offer59_I滑动窗口的最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) {
            return new int[]{};
        }
        int limit = nums.length-k;
        int[] res = new int[limit+1];
        LinkedList<Integer> dequeue = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            int num = nums[i];

            // dequeue保持单调递减，移除比当前元素小的数
            while (!dequeue.isEmpty() && dequeue.peekLast() < num) {
                dequeue.pollLast();
            }
            // 将当前元素插入
            dequeue.addLast(num);
            // 移除已滑过的元素
            if(i-k>=0 && dequeue.peekFirst() == nums[i-k]){
                dequeue.pollFirst();
            }

            // 将dequeue头部元素放入结果数组
            int resIndex = i - k + 1;
            if(resIndex >= 0) {
                res[resIndex] = dequeue.peekFirst();
            }
        }
        return res;
    }

//    public int[] maxSlidingWindow(int[] nums, int k) {
//        if(nums.length == 0) {
//            return new int[]{};
//        }
//        int limit = nums.length-k;
//        int[] res = new int[limit+1];
//        for(int i=0;i<=limit;i++){
//            int max = nums[i];
//            for(int j=1;j<k;j++) {
//                max = Math.max(max,nums[i+j]);
//            }
//            res[i] = max;
//        }
//        return res;
//    }
}
