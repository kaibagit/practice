package me.luliru.parctice.algorithm.binary_search;

/**
 * LC162_寻找峰值
 * Created by luliru on 2021/3/8.
 */
public class LC162_寻找峰值 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(new LC162_寻找峰值().findPeakElement_210308_v3(nums));
    }

    /**
     * 暴力法
     * @param nums
     * @return
     */
    public int findPeakElement_210308_v1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((i - 1 < 0 || nums[i] > nums[i - 1]) && (i + 1 >= n || nums[i] > nums[i + 1])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 线性扫描
     * @param nums
     * @return
     */
    public int findPeakElement_210308_v2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return n - 1;
    }

    /**
     * 二分查找
     * @param nums
     * @return
     */
    public int findPeakElement_210308_v3(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                // 升序，取(mid, right]
                left = mid + 1;
            } else if (nums[mid] > nums[mid + 1]) {
                // 降序，取[left, mid]
                right = mid;
            }
        }
        return left;
    }
}
