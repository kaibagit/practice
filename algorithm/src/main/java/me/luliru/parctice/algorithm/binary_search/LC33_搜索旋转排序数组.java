package me.luliru.parctice.algorithm.binary_search;

/**
 * LC33_搜索旋转排序数组
 * Created by luliru on 2021/2/22.
 */
public class LC33_搜索旋转排序数组 {

    public static void main(String[] args) {
//        int[] nums = new int[]{4,5,6,7,0,1,2};
//        new LC33_搜索旋转排序数组().search(nums, 3);

        int[] nums = new int[]{3, 1};
        new LC33_搜索旋转排序数组().search(nums, 1);
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {     // 考虑nums只有一个元素的情况
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {                               // 左边有序
                if (nums[left] <= target && target < nums[mid]) {      // 目标在左边有序区间
                    right = mid - 1;
                } else {                                                // 目标在右边无序区间
                    left = mid + 1;
                }

            } else {                                                    // 右边有序
                if (nums[mid] < target && target <= nums[right]) {     // 目标在右边有序区间
                    left = mid + 1;
                } else {                                                // 目标在左边无序区间
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
