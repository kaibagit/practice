package me.luliru.parctice.algorithm.dp;

/**
 * 打家劫舍
 * Created by luliru on 2020/10/6.
 */
public class 打家劫舍 {

    public int rob(int[] nums) {
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
