package me.luliru.parctice.algorithm.dp;

/**
 * 乘积最大子数组
 * Created by luliru on 2021/2/15.
 */
public class 乘积最大子数组 {

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxProduct_v1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int maxProduct = nums[0];
        // 对于乘法，我们需要注意，负数乘以负数，会变成正数，所以解这题的时候我们需要维护两个变量，当前的最大值，以及最小值，最小值可能为负数，但没准下一步乘以一个负数，当前的最大值就变成最小值，而最小值则变成最大值了。
        // 我们的动态方程可能这样：
        // maxDP[i + 1] = max(maxDP[i] * A[i + 1], A[i + 1],minDP[i] * A[i + 1])
        // minDP[i + 1] = min(minDP[i] * A[i + 1], A[i + 1],maxDP[i] * A[i + 1])
        // dp[i + 1] = max(dp[i], maxDP[i + 1])
        int[] maxDp = new int[n];
        int[] minDp = new int[n];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int mum = nums[i];
            maxDp[i] = Math.max(Math.max(maxDp[i - 1] * mum, minDp[i - 1] * mum), mum);
            minDp[i] = Math.min(Math.min(maxDp[i - 1] * mum, minDp[i - 1] * mum), mum);
            maxProduct = Math.max(maxDp[i], maxProduct);
        }
        return maxProduct;
    }

    /**
     * 动态规划+空间优化
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int maxProduct = nums[0], max = nums[0], min = nums[0];
        for (int i = 1; i < n; i++) {
            int mum = nums[i];
            int maxBefore = max, minBefore = min;
            max = Math.max(Math.max(maxBefore * mum, minBefore * mum), mum);
            min = Math.min(Math.min(maxBefore * mum, minBefore * mum), mum);
            maxProduct = Math.max(max, maxProduct);
        }
        return maxProduct;
    }
}
