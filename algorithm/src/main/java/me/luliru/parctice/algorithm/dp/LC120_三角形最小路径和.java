package me.luliru.parctice.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 三角形最小路径和
 * Created by luliru on 2021/2/15.
 */
public class LC120_三角形最小路径和 {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<Integer>(){
            {
                add(2);
            }
        });
        triangle.add(new ArrayList<Integer>(){
            {
                add(3);
                add(4);
            }
        });
        triangle.add(new ArrayList<Integer>(){
            {
                add(6);
                add(5);
                add(7);
            }
        });
        triangle.add(new ArrayList<Integer>(){
            {
                add(4);
                add(1);
                add(8);
                add(3);
            }
        });
        new LC120_三角形最小路径和().minimumTotal(triangle);
    }


    /**
     * 递归
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int val = triangle.get(i).get(j);
                dp[j] = Math.min(dp[j], dp[j + 1]) + val;
            }
        }
        return dp[0];
    }



    /**
     * DFS，自底向上，存在重复计算
     */

    public int minimumTotal_210303_v1(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size() - 1) {   // 已经是最底下一行
            return triangle.get(row).get(col);
        }

        int left = dfs(triangle, row + 1, col);
        int right = dfs(triangle, row + 1, col + 1);
        return triangle.get(row).get(col) + Math.min(left, right);
    }


    /**
     * 动态规划，自顶向下
     */
    public int minimumTotal_210302_v2(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }

        int n = triangle.size();
        int[][] minSumDp = new int[n][n];
        minSumDp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int topLeft = j >  0 ? minSumDp[i - 1][j - 1] : Integer.MAX_VALUE;
                int topRight = j <= i - 1 ? minSumDp[i - 1][j] : Integer.MAX_VALUE;
                minSumDp[i][j] = triangle.get(i).get(j) + Math.min(topLeft, topRight);
            }
        }

        int minSum = minSumDp[n -1][0];
        for (int i = 1; i < n; i++) {
            minSum = Math.min(minSumDp[n - 1][i],  minSum);
        }
        return minSum;
    }


    /**
     * 动态规划+自底向上
     * @param triangle
     * @return
     */
    public int minimumTotal_210302_v3(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }

        int n = triangle.size();
        int[][] minSumDp = new int[n][n];
        for (int i = 0; i < n; i++) {
            minSumDp[n - 1][i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--){
            for (int j = 0; j <= i; j++) {
                minSumDp[i][j] = Math.min(minSumDp[i + 1][j], minSumDp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }

        return minSumDp[0][0];
    }

    /**
     * 动态规划+自底向上+空间优化
     * @param triangle
     * @return
     */
    public int minimumTotal_210302_v4(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }

        int n = triangle.size();
        int[] minSumDp = new int[n];
        for (int i = 0; i < n; i++) {
            minSumDp[i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--){
            for (int j = 0; j <= i; j++) {
                minSumDp[j] = Math.min(minSumDp[j], minSumDp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return minSumDp[0];
    }






















    /**
     * 动态规划
     * @param triangle
     * @return
     */
    public int minimumTotal_old(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < (i + 1); j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min,dp[n - 1][i]);
        }
        return min;
    }

    /**
     * 动态规划+空间优化
     * @param triangle
     * @return
     */
    public int minimumTotal_v2(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        int n = triangle.size();
        // 我们从 i 到 0 递减地枚举 j，这样我们只需要一个长度为 n 的一维数组 f，就可以完成状态转移。
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] = dp[0] + triangle.get(i).get(0);
        }

        int min = dp[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min,dp[i]);
        }
        return min;
    }
}
