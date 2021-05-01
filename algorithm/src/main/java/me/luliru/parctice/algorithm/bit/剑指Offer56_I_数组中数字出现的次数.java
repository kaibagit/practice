package me.luliru.parctice.algorithm.bit;

/**
 * 剑指Offer56_I_数组中数字出现的次数
 * Created by luliru on 2021/3/16.
 */
public class 剑指Offer56_I_数组中数字出现的次数 {

    public int[] singleNumbers_210316(int[] nums) {
        // 1、求出所有数异或的结果，最终可能是2个只出现一次数字的异或
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }

        // 2、找到异或结果中，第一位是1的位置，即mark
        int mark = 1;
        while ((mark & result) == 0) {
            mark <<= 1;
        }

        // 将所有数与mark &，如果等于0，则分到一组，不为0分到另一组，相同的数必然分到一组，而两个只出现一次的数，会分到2个不同组；对2个分组分别异或就能取得结果
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & mark) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }

        return new int[]{a, b};
    }
}
