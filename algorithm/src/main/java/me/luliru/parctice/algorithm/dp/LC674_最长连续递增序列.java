package me.luliru.parctice.algorithm.dp;

/**
 * LC674_最长连续递增序列
 * Created by luliru on 2021/2/22.
 */
public class LC674_最长连续递增序列 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,4,7};
        new LC674_最长连续递增序列().findLengthOfLCIS(nums);
    }

    /**
     * 暴力破解
     * @param nums
     * @return
     */
    public int findLengthOfLCIS_v1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j - 1] >= nums[j]) break;
                maxLength = Math.max(j - i + 1, maxLength);
            }
        }
        return maxLength;
    }

    /**
     * dp
     * @param nums
     * @return
     */
    public int findLengthOfLCIS_v2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int maxLength = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            maxLength = Math.max(dp[i], maxLength);
        }

        return maxLength;
    }

    /**
     * dp，空间优化
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int maxLength = 1;
        int preLength = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                ++preLength;
            } else {
                preLength = 1;
            }
            maxLength = Math.max(preLength, maxLength);
        }

        return maxLength;
    }
}
