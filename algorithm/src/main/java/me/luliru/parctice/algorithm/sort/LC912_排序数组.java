package me.luliru.parctice.algorithm.sort;

/**
 * LC912_排序数组
 * Created by luliru on 2021/4/8.
 */
public class LC912_排序数组 {

    public static void main(String[] args) {
        new LC912_排序数组().sortArray_210408(new int[]{5,2,3,1});
    }

    /**
     * 快速排序
     * @param nums
     * @return
     */
    public int[] sortArray_210408(int[] nums) {
        recurse(nums, 0, nums.length - 1);
        return nums;
    }

    private void recurse(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int base = nums[start];
        int left = start, right = end;
        while (left < right) {
            while (right > left & nums[right] >= base) {
                --right;
            }
            nums[left] = nums[right];
            while (right > left & nums[left] < base) {
                ++left;
            }
            nums[right] = nums[left];
        }
        nums[left] = base;
        recurse(nums, start, left - 1);
        recurse(nums, left + 1, end);
    }
}
