package me.luliru.parctice.algorithm.bit;

/**
 * 比特位计数
 * Created by luliru on 2021/2/14.
 */
public class LC338_比特位计数 {


    /**
     * 暴力法，一个个数
     * @param num
     * @return
     */
    public int[] countBits_210302_v1(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int count = 0;
            int val = i;
            while (val != 0) {
                if (val % 2 == 1) {
                    ++count;
                }
                val = val / 2;
            }
            ans[i] = count;
        }
        return ans;
    }

    /**
     * 位运算
     * @param num
     * @return
     */
    public int[] countBits_210302_v2(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int count = 0;
            int val = i;
            while (val != 0) {
                val = val & (val - 1);
                ++count;
            }
            ans[i] = count;
        }
        return ans;
    }

    /**
     * 位运算+动态规划
     * @param num
     * @return
     */
    public int[] countBits_210302_v3(int num) {
        int[] ans = new int[num + 1];
        if (num < 1){
            return ans;
        }
        ans[1] = 1;
        for (int i = 2; i <= num; i++) {
            if ((i & 1) == 0){      // 偶数，等于val/2
                ans[i] = ans[i >> 1];
            }else {                 // 奇数，等于前一个偶数值+1
                ans[i] = ans[i - 1] + 1;
            }
        }
        return ans;
    }




































    /**
     * 遍历+1计数
     * @param num
     * @return
     */
    public int[] countBits_v1_old(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            ans[i] = countOnes(i);
        }
        return ans;
    }

    private int countOnes(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 动态规划 + 最后设置位
     * @param num
     * @return
     */
    public int[] countBits_v2_old(int num) {
        int[] ans = new int[num + 1];
        if (num == 0) {
            return ans;
        }
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    /**
     * 动态规划 + 奇偶转移
     * @param num
     * @return
     */
    public int[] countBits_old(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            if ((i & 1) == 1) {     // 奇数
                ans[i] = ans[i - 1] + 1;
            } else {        // 偶数
                ans[i] = ans[i / 2];
            }
        }
        return ans;
    }
}
