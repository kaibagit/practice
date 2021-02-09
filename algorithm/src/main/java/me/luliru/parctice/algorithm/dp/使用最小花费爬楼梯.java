package me.luliru.parctice.algorithm.dp;

/**
 * 使用最小花费爬楼梯
 * Created by luliru on 2020/12/21.
 */
public class 使用最小花费爬楼梯 {

    /**
     * dp，优化
     * @param cost
     * @return
     */
    public int minCostClimbingStairs_v2(int[] cost) {
        int dpOne = 0;
        int dpTwo = 0;
        for (int i = 2; i <= cost.length; i++) {
            int min = Math.min(dpOne + cost[i - 1], dpTwo + cost[i - 2]);
            dpTwo = dpOne;
            dpOne = min;
        }
        return dpOne;
    }

    /**
     * dp，使用数组
     * @param cost
     * @return
     */
    public int minCostClimbingStairs_v1(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }
}
