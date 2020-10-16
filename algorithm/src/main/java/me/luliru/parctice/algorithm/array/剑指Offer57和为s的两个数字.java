package me.luliru.parctice.algorithm.array;

/**
 * 剑指Offer57和为s的两个数字
 * Created by luliru on 2020/10/13.
 */
public class 剑指Offer57和为s的两个数字 {

    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            int left = nums[i];
            int require = target - left;
            int j = nums.length -1;
            while (i != j) {
                int right = nums[j];
                if(require == right) {
                    return new int[]{left,right};
                }else if(require > right) {
                    break;
                } else {
                    j--;
                }
            }
        }
        return new int[0];
    }
}
