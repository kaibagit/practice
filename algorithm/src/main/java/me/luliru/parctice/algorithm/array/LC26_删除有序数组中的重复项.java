package me.luliru.parctice.algorithm.array;

/**
 * LC26_删除有序数组中的重复项
 * Created by luliru on 2021/4/18.
 */
public class LC26_删除有序数组中的重复项 {

    /**
     * 双指针
     * @param nums
     * @return
     */
    public int removeDuplicates_210418(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int slow = 0, fast = 1;
        while (fast < len) {    // 退出条件：fast = len
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
            ++fast;
        }
        return slow + 1;
    }
}
