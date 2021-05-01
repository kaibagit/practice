package me.luliru.parctice.algorithm.dp;

/**
 * LC121_买卖股票的最佳时机
 * Created by luliru on 2021/3/2.
 */
public class LC121_买卖股票的最佳时机 {

    public static void main(String[] args) {
        new LC121_买卖股票的最佳时机().maxProfit(new int[]{7,1,5,3,6,4});
    }

    /**
     * 暴力求解
     * @param prices
     * @return
     */
    public int maxProfit_210302_v1(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int buy = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                int sell = prices[j];
                if (sell > buy) {
                    maxProfit = Math.max(sell - buy, maxProfit);
                }
            }
        }

        return maxProfit;
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

        int[] minPrices = new int[prices.length];
        minPrices[0] = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrices[i - 1]) {
                maxProfit = Math.max(prices[i] - minPrices[i - 1], maxProfit);
                minPrices[i] = minPrices[i - 1];
            } else {
                minPrices[i] = prices[i];
            }
        }

        return maxProfit;
    }

    /**
     * 动态规划+空间优化
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrice) {
                maxProfit = Math.max(prices[i] - minPrice, maxProfit);
            } else {
                minPrice = prices[i];
            }
        }

        return maxProfit;
    }
}
