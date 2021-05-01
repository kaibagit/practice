package me.luliru.parctice.algorithm.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LC128_最长连续序列
 * Created by luliru on 2021/2/22.
 */
public class LC128_最长连续序列 {

    /**
     * 排序
     * @param nums
     * @return
     */
    public int longestConsecutive_v1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int longest = 1;
        int length = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                ++length;
                longest = Math.max(length, longest);
            } else if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                length = 1;
            }
        }
        return longest;
    }

    /**
     * hash+dp
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int longest = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        for (int num : set) {
            if (set.contains(num - 1)) {        // 优化掉重新计算
                continue;
            }
            int length = 1;
            int begin = num;
            while (set.contains(begin + 1)) {
                ++begin;
                ++length;
            }
            longest = Math.max(length, longest);
        }
        return longest;
    }
}
