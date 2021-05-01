package me.luliru.parctice.algorithm.binary_search;

/**
 * LC81_搜索旋转排序数组II
 * Created by luliru on 2021/3/11.
 */
public class LC81_搜索旋转排序数组II {

    public static void main(String[] args) {
//        int[] nums = new int[]{2,5,6,0,0,1,2};
//        new LC81_搜索旋转排序数组II().search(nums, 0);

//        int[] nums = new int[]{1,0,1,1,1};
//        new LC81_搜索旋转排序数组II().search(nums, 0);

//        int[] nums = new int[]{4,5,6,7,0,1,2};
//        System.out.println(new LC81_搜索旋转排序数组II().search(nums, 5));

        int[] nums = new int[]{3,1,2,2,2};
        System.out.println(new LC81_搜索旋转排序数组II().search_210311(nums, 1));
    }


    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public boolean search_210407(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] == target || nums[mid] == target || nums[right] == target) {
                return true;
            }

            if (nums[left] < nums[mid]) {
                if (nums[left] < target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid]) {
                left = left + 1;
            } else if (nums[left] > nums[mid]) {
                if (nums[mid] < target && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }



































    public boolean search_210311(int[] nums, int target) {
        // 旋转的排序数组，经过中间二分之后，左右两个区间，最少有一个是有序的;也有可能是两个都是有序的
        int left = 0, right = nums.length - 1;
        while (left <= right) {     // 退出条件：left > right
            int mid = left + (right - left) / 2;
            if (target == nums[left] || target == nums[mid] || target == nums[right]){
                return true;
            }
            if (nums[left] < nums[mid]) {       // 左边有序
                if (nums[left] < target && target < nums[mid]) {    // target在有序数组中间，则继续查找
                    right = mid - 1;
                } else {    // target不在有序数组中，则尝试在右区间查找
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid])  {
                // 有可能出现1,0,1查找0的情况
                left = left + 1;
            } else if (nums[left] > nums[mid]) {    // 右边有序
                if (nums[mid] < target && target < nums[right]) {   // target在有序数组中间，则继续查找
                    left = mid + 1;
                } else {    // target不在有序数组中，则尝试在左区间查找
                    right = mid - 1;
                }
            }
        }

        return false;
    }
}
