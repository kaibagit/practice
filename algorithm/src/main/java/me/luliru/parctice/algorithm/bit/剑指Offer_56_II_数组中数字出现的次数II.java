package me.luliru.parctice.algorithm.bit;

/**
 * 剑指Offer_56_II_数组中数字出现的次数II
 * Created by luliru on 2021/3/16.
 */
public class 剑指Offer_56_II_数组中数字出现的次数II {

    /**
     * 位运算 + 统计
     * @param nums
     * @return
     */
    public int singleNumber_210316(int[] nums) {
        int ans = 0;

        for (int mark = 1, count = 1; count <= 31; mark <<= 1, count++) {
            int positionCount = 0;
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i] & mark) == mark) {
                    ++positionCount;
                }
            }
            if (positionCount % 3 != 0) {
                ans += mark;
            }
        }

        return ans;
    }
}
