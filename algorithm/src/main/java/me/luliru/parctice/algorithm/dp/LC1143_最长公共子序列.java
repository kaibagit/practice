package me.luliru.parctice.algorithm.dp;

/**
 * LC1143_最长公共子序列
 * Created by luliru on 2021/4/3.
 */
public class LC1143_最长公共子序列 {

    /**
     * 递归 + 记忆化搜索
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence_210403_v1(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        Integer[][] mem = new Integer[m + 1][n + 1];
        return recurse(text1, text2, m, n, mem);
    }

    private int recurse(String text1, String text2, int len1, int len2, Integer[][] mem) {
        int len = 0;
        if (len1 == 0 || len2 == 0) {
            return len;
        }
        if (mem[len1][len2] != null) {
            return mem[len1][len2];
        }
        if (text1.charAt(len1 - 1) == text2.charAt(len2 - 1)) {
            len = recurse(text1, text2, len1 - 1, len2 - 1, mem) + 1;
        } else {
            len = Math.max(recurse(text1, text2, len1 - 1, len2, mem), recurse(text1, text2, len1, len2 - 1, mem));
//            len = Math.max(len, recurse(text1, text2, len1 - 1, len2 - 1, mem));
        }
        mem[len1][len2] = len;
        return len;
    }

    /**
     * 动态规划
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence_210403_v2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
