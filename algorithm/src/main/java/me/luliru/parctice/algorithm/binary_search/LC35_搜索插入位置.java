package me.luliru.parctice.algorithm.binary_search;

/**
 * LC35_搜索插入位置
 * Created by luliru on 2021/3/5.
 */
public class LC35_搜索插入位置 {


    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert_210308(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {     // 退出条件：left = right + 1，此时left的是第一个>target的值，正好是要插入的位置
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return left;
    }






































    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert_210305(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return left;
    }
}
