package me.luliru.parctice.algorithm.dp;

/**
 * LC494_目标和
 * Created by luliru on 2021/3/27.
 */
public class LC494_目标和 {

    public static void main(String[] args) {
        new LC494_目标和().findTargetSumWays(new int[]{1,1,1,1,1}, 3);
    }

    public int findTargetSumWays(int[] nums, int S) {
        int len = nums.length;
        int[][] dp = new int[len + 1][S + 21 + 1 + 2];  // 往左偏移21个位置，再加上0，就是S + 1，再加上2，因为要考虑j + 2
        dp[0][21] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = -20; j <= S; j++) {
                // dp[i][j] = dp[i - 1][j - 1] + dp[i -1][j + 2]
                dp[i][j + 21] = dp[i - 1][j - 1 + 21] + dp[i - 1][j + 2 + 21];
            }
        }
        return dp[len][S + 21];
    }
}
