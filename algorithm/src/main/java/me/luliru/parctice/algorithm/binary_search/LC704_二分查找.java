package me.luliru.parctice.algorithm.binary_search;

/**
 * LC704_二分查找
 * Created by luliru on 2021/3/5.
 */
public class LC704_二分查找 {


    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search_210305_v2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;  //搜索范围：[left,right]；
        while (left <= right) {     // 这里nums.length = 0 不会进入循环
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }
}
