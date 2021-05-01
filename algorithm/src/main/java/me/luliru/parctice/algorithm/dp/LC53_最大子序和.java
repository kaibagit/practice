package me.luliru.parctice.algorithm.dp;

/**
 * 最大子序和
 * Created by luliru on 2020/9/25.
 */
public class LC53_最大子序和 {

    public static void main(String[] args) {
        LC53_最大子序和 question = new LC53_最大子序和();
        int ans = question.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(ans);
    }


    /**
     * 暴力法
     * 时间复杂度：O(n^2)
     * @param nums
     * @return
     */
    public int maxSubArray_210302_v1(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }


    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int preSum = nums[0];
        for (int i = 1; i < n; i++) {
            int currSum = Math.max(nums[i], preSum + nums[i]);
            preSum = currSum;

            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
































    public int maxSubArray_old(int[] nums) {
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
