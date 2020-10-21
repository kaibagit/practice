package me.luliru.parctice.algorithm.dp;

/**
 * 剑指Offer63股票的最大利润
 * Created by luliru on 2020/10/20.
 */
public class 剑指Offer63股票的最大利润 {

    public static void main(String[] args) {
        new 剑指Offer63股票的最大利润().maxProfit(new int[]{1,2});
    }

    public int maxProfit(int[] prices) {
        // 特殊输入处理
        if(prices .length < 2) {
            return 0;
        }

        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i=1;i<prices.length;i++) {
            int curr = prices[i];
            if (curr > minPrice) {
                int profit = curr - minPrice;
                maxProfit = Math.max(maxProfit,profit);
            } else {
                minPrice = curr;
            }
        }

        return maxProfit;
    }


}
