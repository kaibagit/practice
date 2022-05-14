package me.luliru.parctice.algorithm.dp;

/**
 * LC70_爬楼梯
 * Created by luliru on 2022/5/10.
 */
public class LC70_爬楼梯 {

    /**
     * 动态规划
     * @param n
     * @return
     */
    public int climbStairs_dp(int n) {
        // 处理边界
        if (n == 1) return 1;
        if (n == 2) return 2;

        // dp[i] = dp[i - 1] + dp[i - 2]
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * 动态规划+空间优化
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // 处理边界
        if (n == 1) return 1;
        if (n == 2) return 2;

        int minus2 = 1;
        int minus1 = 2;
        for (int i = 3; i <= n; i++) {
            int sum = minus1 + minus2;
            // 下一轮迭代
            minus2 = minus1;
            minus1 = sum;
        }

        return minus1;
    }
}
