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
     * 贪心+二分查找
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
                int index = search(dp,dpMaxIndex+1,currVal);
                dp[index] = currVal;
            }
        }

        return dpMaxIndex+1;
    }

    /**
     * 我们维护一个slow列表表示nums[]中的最慢上升序列，遍历nums，对于一个nums[i]，若：
     * nums[i] > slow的最后一个元素（即大于slow中的所有元素），就将加入到slow的最后面
     * nums[i] <= slow的最后一个元素，我们就查找slow列表中第一个大于等于nums[i]的数并替换它。由于非严格单调递增的序列，我们很容易的发现，可以使用二分法来查找这个数。这一步的时间复杂度便降到了log(n)
     *
     * @param arr
     * @param length
     * @param target
     * @return
     */
    private int search(int[] arr,int length,int target) {
        int left =0;
        int right = length;
        while (left < right) {
            int mid = left + (right-left) /2;
            if(arr[mid] < target) {
                left = mid+1;
            }else if(arr[mid] > target) {
                right = mid;
            }else if(arr[mid] == target) {
                left = mid+1;
            }
        }
        return right -1;
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
