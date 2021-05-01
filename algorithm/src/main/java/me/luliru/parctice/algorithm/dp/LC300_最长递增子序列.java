package me.luliru.parctice.algorithm.dp;

/**
 * 最长上升子序列
 * Created by luliru on 2020/10/16.
 */
public class LC300_最长递增子序列 {

    public static void main(String[] args) {
//        new LC300_最长递增子序列().lengthOfLIS_v1(new int[]{0});
//        System.out.println(new LC300_最长递增子序列().lengthOfLIS_210311_v2(new int[]{-2,-1}));
        System.out.println(new LC300_最长递增子序列().lengthOfLIS_210311_v2(new int[]{10,9,2,5,3,7,101,18}));
    }


    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int lengthOfLIS_210303(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }

        int ans = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int length = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    length = Math.max(dp[j] + 1, length);
                }
            }
            dp[i] = length;
            ans = Math.max(length, ans);
        }

        return ans;
    }


    /**
     * 贪心+单调递增数组
     * @param nums
     * @return
     */
    public int lengthOfLIS_210311_v1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int maxLength = 1;
        int[] minDp = new int[n + 1];   // minDp[i]表示当最大长度为i的所有链路中，末尾数当中最小的那个值
        minDp[1] = nums[0];
        for (int i = 1; i < n; i++) {
            int curr = nums[i];
            // 写法一：
//            if (curr > minDp[maxLength]) {
//                ++maxLength;
//                minDp[maxLength] = curr;
//            } else {
//                // 从前往后找，找到第一个比curr大的数，将该位置值变更为curr
//                for (int j = 1; j <= maxLength; j++) {
//                    if (minDp[j] >= curr) {
//                        minDp[j] = curr;
//                        break;
//                    }
//                }
//            }

            // 写法二：找到比curr小的最后一个元素位置，然后+1，将该位置设置为curr
            int lastIndex = 1;
            while (lastIndex <= maxLength && minDp[lastIndex] < curr) {
                ++lastIndex;
            }
            minDp[lastIndex] = curr;

            maxLength = Math.max(lastIndex, maxLength);
        }
        return maxLength;
    }

    /**
     * 贪心+单调递增数组+二分
     * @param nums
     * @return
     */
    public int lengthOfLIS_210311_v2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int maxLength = 1;
        int[] minDp = new int[n + 1];   // minDp[i]表示当最大长度为i的所有链路中，末尾数当中最小的那个值
        minDp[1] = nums[0];
        for (int i = 1; i < n; i++) {
            int curr = nums[i];
            if (curr > minDp[maxLength]) {
                ++maxLength;
                minDp[maxLength] = curr;
            } else {
                // 找到第一个>=curr的数
                int left = 1, right = maxLength;
                while (left < right) {     // 退出条件：left > right
                    int mid = left + (right - left) / 2;
                    if (curr < minDp[mid]) {
                        right = mid;
                    } else if (curr == minDp[mid]) {
                        right = mid;
                    } else if (curr > minDp[mid]) {
                        left = mid + 1;
                    }
                }
                minDp[left] = curr;
            }
        }
        return maxLength;
    }


































    /**
     * 贪心+二分查找
     * @param nums
     * @return
     */
    public int lengthOfLIS_old(int[] nums) {
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
        for (int i = 0; i < nums.length; i++) {
            int currVal = nums[i];
            int currMaxCount = 1;
            for (int j = 0; j < i; j++) {
                if (currVal > nums[j]) {
                    currMaxCount = Math.max(currMaxCount, dpArr[j] + 1);
                }
            }
            dpArr[i] = currMaxCount;
            maxCount = Math.max(maxCount, currMaxCount);
        }

        return maxCount;
    }
}
