package me.luliru.parctice.algorithm.dp;

/**
 * LC746_使用最小花费爬楼梯
 * Created by luliru on 2022/5/10.
 */
public class LC746_使用最小花费爬楼梯 {

    /**
     * 动态规划
     * @param cost
     * @return
     */
    public int minCostClimbingStairs_dp(int[] cost) {
        // dp[i] = min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2])
        int[] dp = new int[cost.length + 1];
        for (int i = 2; i <= cost.length; i++) {
            int minSum = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);
            dp[i] = minSum;
        }

        return dp[cost.length];
    }

    /**
     * 动态规划+空间优化
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        // dp[i] = min(cost[i - 1] + minus1, cost[i - 2] + minus2)
        int minus1 = 0;
        int minus2 = 0;
        for (int i = 2; i <= cost.length; i++) {
            int minSum = Math.min(cost[i - 1] + minus1, cost[i - 2] + minus2);
            // 下一轮迭代
            minus2 = minus1;
            minus1 = minSum;
        }

        return minus1;
    }

}
