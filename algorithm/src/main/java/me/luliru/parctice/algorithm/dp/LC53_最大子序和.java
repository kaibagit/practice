package me.luliru.parctice.algorithm.dp;

/**
 * 最大子序和
 * Created by luliru on 2020/9/25.
 */
public class LC53_最大子序和 {

    public static void main(String[] args) {
        LC53_最大子序和 question = new LC53_最大子序和();
//        int ans = question.maxSubArray_dp(new int[]{-2,1,-3,4,-1,2,1,-5,4});
//        System.out.println(ans);

        int ans = question.maxSubArray_dp(new int[]{5,4,-1,7,8});
        System.out.println(ans);
    }


    /**
     * 暴力解
     * 时间复杂度：O(n^2)
     * @param nums
     * @return
     */
    public int maxSubArray_loop(int[] nums) {
        // 如果只有一个负数，则必选，所以初始不能为0
        int maxSum = nums[0];
        // 双重循环，计算从i到末尾的总和
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    /**
     * 贪心
     * 时间复杂度：O(n)
     * @param nums
     * @return
     */
    public int maxSubArray_greedy(int[] nums) {
        int maxSum = nums[0];
        int lastSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lastSum > 0) {
                lastSum += nums[i];
            } else {
                lastSum = nums[i];
            }
            maxSum = Math.max(maxSum, lastSum);
        }
        return maxSum;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray_dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < n; i++) {
            // 状态转化：dp[i] = max(dp[i - 1] + nums[i], nums[i])
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    public int maxSubArray_dp_plus(int[] nums) {
        int n = nums.length;
        int preSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < n; i++) {
            // 状态转化：dp[i] = max(dp[i - 1] + nums[i], nums[i])
            // 只保留前一个dp[i - 1]即可
            preSum = Math.max(preSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }






































    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray_dp_old(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int preSum = nums[0];
        for (int i = 1; i < n; i++) {
            int currSum = Math.max(nums[i], preSum + nums[i]);
            preSum = currSum;

            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
































    public int maxSubArray_old(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        Integer maxSum = nums[0];
        int lastSum = nums[0];
        for(int i=1;i<nums.length;i++) {
            int val = nums[i];
            lastSum = Math.max(lastSum + val,val);
            if(lastSum > maxSum) {
                maxSum = lastSum;
            }
        }
        return maxSum;
    }
}
