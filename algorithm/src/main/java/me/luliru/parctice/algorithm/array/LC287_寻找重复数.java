package me.luliru.parctice.algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LC287_寻找重复数
 * Created by luliru on 2021/3/13.
 */
public class LC287_寻找重复数 {

    /**
     * 哈希
     * @param nums
     * @return
     */
    public int findDuplicate_210313_v1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }

    /**
     * 二分法
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/zhe-ge-shu-zu-you-dian-te-shu-suo-yi-ke-yi-yong-ku/
     * @param nums
     * @return
     */
    public int findDuplicate_210313_v2(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int notMore = 0;
            // 统计小于等于mid的个数
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    ++notMore;
                }
            }
            if (notMore > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
