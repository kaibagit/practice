package me.luliru.parctice.algorithm.dp;

/**
 * LC213_打家劫舍II
 * Created by luliru on 2021/3/28.
 */
public class LC213_打家劫舍II {

    public int rob_210328(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        // dp[i] = max{dp[i - 2] + nums[i], dp[i - 1]}
        // 1、计算第一间偷了，相当于少了最后一间房
        int pre_2 = nums[0];
        int pre_1 = Math.max(pre_2, nums[1]);
        for (int i = 2; i < len - 1; i++) {
            int curr = Math.max(pre_2 + nums[i], pre_1);
            pre_2 = pre_1;
            pre_1 = curr;
        }
        int max = pre_1;

        // 2、计算第一间没偷
        pre_2 = 0;
        pre_1 = nums[1];
        for (int i = 2; i < len; i++) {
            int curr = Math.max(pre_2 + nums[i], pre_1);
            pre_2 = pre_1;
            pre_1 = curr;
        }

        return Math.max(max, pre_1);
    }
}
