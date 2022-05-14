package me.luliru.parctice.algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指Offer03_数组中重复的数字
 * Created by luliru on 2022/5/12.
 */
public class 剑指Offer03_数组中重复的数字 {

    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return nums[i];
            }
        }
        return 0;
    }
}
