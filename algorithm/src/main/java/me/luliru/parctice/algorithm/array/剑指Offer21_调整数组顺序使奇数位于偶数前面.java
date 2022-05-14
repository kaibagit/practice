package me.luliru.parctice.algorithm.array;

/**
 * 剑指Offer21_调整数组顺序使奇数位于偶数前面
 * Created by luliru on 2022/5/12.
 */
public class 剑指Offer21_调整数组顺序使奇数位于偶数前面 {

    public int[] exchange(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            // left为奇数
            if ((nums[left] & 1) == 1) {
                left++;
                continue;
            }

            // left为偶数
            while (right > left) {
                // right为偶数
                if ((nums[right] & 1) == 0) {
                    right--;
                    continue;
                }

                // right为奇数，开始互换
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
                break;
            }
        }

        return nums;
    }
}
