package me.luliru.parctice.algorithm.dp;

/**
 * 最长上升子序列
 * Created by luliru on 2020/10/16.
 */
public class 最长上升子序列 {

    public static void main(String[] args) {
        new 最长上升子序列().lengthOfLIS_v1(new int[]{0});
    }

    /**
     * 动态规划（贪心）+二分查找
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int dpMaxIndex = 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1;i<nums.length;i++) {
            int currVal = nums[i];
            if(currVal > dp[dpMaxIndex]) {
                dpMaxIndex++;
                dp[dpMaxIndex] = currVal;
            } else {
                // 找到比currVal小的最大元素
                int left=0;
                int right = dpMaxIndex;
                while (left < right) {
                    int mid = left + (right-left) / 2;
                    if(dp[mid] < currVal) {

                    }else if(dp[mid] >= currVal) {

                    }
                }
                dp[left] = currVal;
            }
        }

        return dpMaxIndex+1;
    }

    /**
     * 动态规划，用一个数组，记录每个元素的最大长度（从0往后一次比，如果当前值比前面元素大，则长度+1，找到这其中最大的那个）
     * @param nums
     * @return
     */
    public int lengthOfLIS_v1(int[] nums) {
        int maxCount = 0;
        int[] dpArr = new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            int currVal = nums[i];
            int currMaxCount = 1;
            for(int j=0;j<i;j++) {
                if(currVal > nums[j]) {
                    currMaxCount = Math.max(currMaxCount,dpArr[j] +1);
                }
            }
            dpArr[i] = currMaxCount;
            maxCount = Math.max(maxCount,currMaxCount);
        }

        return maxCount;
    }
}
