package me.luliru.parctice.algorithm.bit;

/**
 * LC645_错误的集合
 * Created by luliru on 2021/3/16.
 */
public class LC645_错误的集合 {

    /**
     * 异或 + 分组
     * @param nums
     * @return
     */
    public int[] findErrorNums_210316(int[] nums) {
        int n = nums.length;
        // 1、求异或结果
        int result = 0;     // 重复的数，以及丢失的数 的异或结果
        for (int i = 1; i <= n; i++) {
            result ^= i;
            result ^= nums[i - 1];
        }

        // 2、找出那2个数的不同bit位置，然后将nums分成a,b两组，这样，要找的2个值必然被分隔在a,b两组
        int mark = 1;
        while (true) {
            if ((result & mark) == mark) {
                break;
            }

            mark <<= 1;
        }

        // 3、nums和[1,n]分组异或之后，2个值就是要找的结果
        int a = 0, b = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & mark) == mark) {
                a ^= i;
            } else {
                b ^= i;
            }
            if ((nums[i - 1] & mark) == mark) {
                a ^= nums[i - 1];
            } else {
                b ^= nums[i - 1];
            }
        }

        // 4、最后遍历 nums，确定两个数字中哪个为重复数字，哪个为缺失数字
        for (int i = 0; i < n; i++) {
            if (nums[i] == a) {
                return new int[]{a, b};
            }
            if (nums[i] == b) {
                return new int[]{b, a};
            }
        }

        return null;
    }
}
