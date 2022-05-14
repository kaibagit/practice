package me.luliru.parctice.algorithm.greedy;

import java.util.Arrays;

/**
 * 剑指Offer45_把数组排成最小的数
 * Created by luliru on 2022/5/9.
 */
public class 剑指Offer45_把数组排成最小的数 {

    public String minNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStrs, (x, y) -> (x + y).compareTo(y + x));

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numStrs.length; i++) {
            result.append(numStrs[i]);
        }
        return result.toString();
    }
}
