package me.luliru.parctice.algorithm.bit;

/**
 * 只出现一次的数字
 * Created by luliru on 2020/10/18.
 */
public class 只出现一次的数字 {

    public int singleNumber(int[] nums) {
        int x = nums[0];
        for(int i=1;i<nums.length;i++) {
            x = x ^ nums[i];
        }
        return x;
    }
}
