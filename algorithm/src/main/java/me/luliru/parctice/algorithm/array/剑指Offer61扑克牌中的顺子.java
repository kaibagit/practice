package me.luliru.parctice.algorithm.array;

import java.util.Arrays;

/**
 * 剑指Offer61扑克牌中的顺子
 * Created by luliru on 2020/10/14.
 */
public class 剑指Offer61扑克牌中的顺子 {

    public boolean isStraight(int[] nums) {
        int zeroCount = 0;
        Integer lastNum = null;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++) {
            int num = nums[i];
            if(num == 0) {
                zeroCount++;    // 大、小王 计数
            }else if(lastNum == null) {
                lastNum = num;
            } else {
                if(num == lastNum) {
                    return false;
                }
                int borrow = num - lastNum - 1;
                zeroCount = zeroCount - borrow;
                if(zeroCount < 0) {
                    return false;
                }
                lastNum = num;
            }
        }
        return true;
    }
}
