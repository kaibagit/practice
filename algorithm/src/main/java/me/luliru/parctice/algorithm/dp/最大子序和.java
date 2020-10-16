package me.luliru.parctice.algorithm.dp;

/**
 * 最大子序和
 * Created by luliru on 2020/9/25.
 */
public class 最大子序和 {

    public static void main(String[] args) {
        最大子序和 question = new 最大子序和();
        int ans = question.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(ans);
    }

    public int maxSubArray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        Integer maxSum = nums[0];
        int lastSum = nums[0];
        for(int i=1;i<nums.length;i++) {
            int val = nums[i];
            lastSum = Math.max(lastSum + val,val);
            if(lastSum > maxSum) {
                maxSum = lastSum;
            }
        }
        return maxSum;
    }
}
