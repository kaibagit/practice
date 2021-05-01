package me.luliru.parctice.algorithm.array;

/**
 * LC80_删除有序数组中的重复项II
 * Created by luliru on 2021/4/6.
 */
public class LC80_删除有序数组中的重复项II {

    /**
     * 双指针
     * @param nums
     * @return
     */
    public int removeDuplicates_210406(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }

        int slow = 0, fast = 1;
        boolean firstEle = true;    // slow当前元素是第一个出现的
        while (fast < len) {
            if (nums[fast] == nums[slow]) {
                if (firstEle) {
                    ++slow;
                    nums[slow] = nums[fast];
                    firstEle = false;
                }
            } else {
                ++slow;
                nums[slow] = nums[fast];
                firstEle = true;
            }
            ++fast;
        }

        return slow + 1;
    }
}
