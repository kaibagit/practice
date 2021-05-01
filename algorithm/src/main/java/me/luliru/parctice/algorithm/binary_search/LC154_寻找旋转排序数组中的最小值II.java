package me.luliru.parctice.algorithm.binary_search;

/**
 * LC154_寻找旋转排序数组中的最小值II
 * Created by luliru on 2021/3/11.
 */
public class LC154_寻找旋转排序数组中的最小值II {

    public int findMin_210408(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] == nums[right]) {
                right = right - 1;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
        }
        return nums[left];
    }
















































    public int findMin_210311(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {     // 这里其实left < right即可
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {      // 右区间有序
                right = mid;
            } else if (nums[mid] == nums[right]) {  // 无法判断是-，^，还是v，所以缩小范围，但是最小值可能再最左边，所以要从右往左缩
                --right;
            }  else {   // 左区间有序，右区间无序，最小值必然在右区间
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
