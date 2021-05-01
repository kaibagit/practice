package me.luliru.parctice.algorithm.dp;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机IV
 * Created by luliru on 2021/2/15.
 */
public class LC188_买卖股票的最佳时机IV {

    public static void main(String[] args) {
        System.out.println(new LC188_买卖股票的最佳时机IV().maxProfit(2,new int[]{3,2,6,5,0,3}));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;

        // 1、构建动态规划数组
        int[][] notake = new int[n][k + 1];     // 不持有股票[第i天][第k次交易]
        int[][] take = new int[n][k + 1];       // 持有股票

        // 2、初始化第0行数据
        notake[0][0] = 0;
        take[0][0] = -prices[0];
        for (int j = 1; j <= k; j++) {
            notake[0][j] = -2000000;
            take[0][j] = -2000000;
        }

        // 3、根据dp转化公式计算
        // i代表第i天
        // j代表交易j次
        // notake[i, k] = max{notake[i - 1, j], take[i - 1, j - 1] + prices[i]}
        // take[i, k] = max{take[i - 1, j], notake[i - 1, j] - prices[i]}
        for (int i = 1; i < n; i++) {
            notake[i][0] = 0;
            take[i][0] = Math.max(take[i - 1][0], notake[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                notake[i][j] = Math.max(notake[i - 1][j], take[i - 1][j - 1] + prices[i]);
                take[i][j] = Math.max(take[i - 1][j], notake[i - 1][j] - prices[i]);
            }
        }

        // 4、取最后i = n - 1时，所有交易次数k的情况的最大值
        return Arrays.stream(notake[n - 1]).max().getAsInt();
    }







    public int maxProfit_old(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[][] bear = new int[n][k + 1];   // 不持有股票
        int[][] take = new int[n][k + 1];   // 持有股票

        bear[0][0] = 0;             // 0交易次数，收益为0
        take[0][0] = -prices[0];    // 0交易次数，只买入
        for (int i = 1; i <= k; ++i) {
            take[0][i] = bear[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            take[i][0] = Math.max(take[i - 1][0], bear[i - 1][0] - prices[i]);  // 相当于take[i] = max{take[i-1],bear[i-1]-prices[i]}
            for (int j = 1; j <= k; ++j) {
                take[i][j] = Math.max(take[i - 1][j], bear[i - 1][j] - prices[i]); // 与j=0时相同
                bear[i][j] = Math.max(bear[i - 1][j], take[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(bear[n - 1]).max().getAsInt();
    }
}
