package me.luliru.parctice.algorithm.dp;

/**
 * LC122_买卖股票的最佳时机II
 * Created by luliru on 2021/3/2.
 */
public class LC122_买卖股票的最佳时机II {

    /**
     * 贪心
     * @param prices
     * @return
     */
    public int maxProfit_210302_v1(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int profit = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }

        return profit;
    }

    /**
     * 动态规划
     * @param prices
     * @return
     */
    public int maxProfit_210302_v2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[2][prices.length];
        dp[0][0] = 0;           // 不持股
        dp[1][0] = -prices[0];  // 持股
        for (int i = 1; i < prices.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);
            dp[1][i] = Math.max(dp[0][i - 1] - prices[i], dp[1][i - 1]);
        }

        return dp[0][prices.length - 1];
    }

































    /**
     * 动态规划
     * @param prices
     * @return
     */
    public int maxProfit_dp_v1(int[] prices) {
        // dp[0] 不持股,dp[1] 持股
        int[][] dp = new int[2][prices.length];
        dp[0][0] = 0;
        dp[1][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);    // 当天不持股=max{前一天不持股，前一天持股卖出}
            dp[1][i] = Math.max(dp[0][i - 1] - prices[i], dp[1][i - 1]);    // 当天持股=max{前一天不持股+今天买入，前一天持股不动}
        }

        return dp[0][prices.length - 1];
    }

    /**
     * 动态规划+优化
     * @param prices
     * @return
     */
    public int maxProfit_dp_v2(int[] prices) {
        // 动态规划优化，空间复杂度降为O(1)
        int bear = 0;   // 不持股
        int take = -prices[0];  // 持股
        for (int i = 1; i < prices.length; i++) {
            bear = Math.max(bear, take + prices[i]);    // 当天不持股=max{前一天不持股，前一天持股卖出}
            take = Math.max(bear - prices[i], take);    // 当天持股=max{前一天不持股+今天买入，前一天持股不动}
        }

        return bear;
    }


    /**
     * 贪心
     * @param prices
     * @return
     */
    public int maxProfit_greedy(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int curr = prices[i];
            int pre = prices[i - 1];
            if (curr > pre) {   // 后一天价格高，则买入
                profit = profit + curr - pre;
            }
        }
        return profit;
    }
}
