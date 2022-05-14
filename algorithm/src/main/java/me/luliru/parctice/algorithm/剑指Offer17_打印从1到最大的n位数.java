package me.luliru.parctice.algorithm;

/**
 * 剑指Offer17_打印从1到最大的n位数
 * Created by luliru on 2022/5/12.
 */
public class 剑指Offer17_打印从1到最大的n位数 {

    public int[] printNumbers(int n) {
        int max = 1;
        for (int i = 0; i < n; i++) {
            max = max * 10;
        }

        int[] ans = new int[max - 1];
        for (int i = 0; i < max - 1; i++) {
            ans[i] = i + 1;
        }

        return ans;
    }
}
