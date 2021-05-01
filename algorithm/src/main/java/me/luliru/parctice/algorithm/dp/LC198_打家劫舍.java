package me.luliru.parctice.algorithm.dp;

/**
 * LC198_打家劫舍
 * Created by luliru on 2021/3/28.
 */
public class LC198_打家劫舍 {

    /**
     * 动态规划 + 空间优化
     * @param nums
     * @return
     */
    public int rob_210328_v1(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        int notSteal = nums[0];     // 上间房没偷
        int steal = nums[1];        // 上间房偷了
        for (int i = 2; i < len; i++) {
            int currNotSteal = Math.max(notSteal, steal);       // 这间房没偷
            int currSteal = notSteal + nums[i];                 // 这间房偷了
            notSteal = currNotSteal;
            steal = currSteal;
        }

        return Math.max(notSteal, steal);
    }






































    public int rob_old(int[] nums) {
        int nMinus2 = 0;
        int nMinus1 = 0;
        for(int i=0;i<nums.length;i++) {
            int curr = Math.max(nMinus2+nums[i],nMinus1);
            nMinus2 = nMinus1;
            nMinus1 = curr;
        }
        return nMinus1;
    }
}
