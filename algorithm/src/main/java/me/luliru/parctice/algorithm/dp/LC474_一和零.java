package me.luliru.parctice.algorithm.dp;

/**
 * LC474_一和零
 * Created by luliru on 2021/3/26.
 */
public class LC474_一和零 {

    public int findMaxForm_210326(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len][m + 1][n + 1];
        for (int i = 0; i < len; i++) {
            int[] count = countZeroAndOne(strs[i]);
            int zeros = count[0];
            int ones = count[1];

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeros && k >= ones) {
                        if (i > 0) {
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                        } else {
                            dp[i][j][k] = 1;
                        }
                    } else {
                        if (i > 0) {
                            dp[i][j][k] = dp[i - 1][j][k];
                        }
                    }
                }
            }
        }
        return dp[len - 1][m][n];
    }

    private int[] countZeroAndOne(String str) {
        int zeros = 0, ones = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                ++zeros;
            } else {
                ++ones;
            }
        }
        return new int[]{zeros, ones};
    }
}
