package me.luliru.parctice.algorithm.dp;

/**
 * LC303_区域和检索
 * Created by luliru on 2021/3/2.
 */
public class LC303_区域和检索 {

    /**
     * 暴力法
     */
    class NumArray_210302 {

        private int[] nums;
        private int n;

        public NumArray_210302(int[] nums) {
            this.nums = nums;
            n = nums.length;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for (int x = i; x <= j; x++) {
                sum += nums[x];
            }
            return sum;
        }
    }

    /**
     * 动态规划
     */
    class NumArray_210302_v2 {

        private int[] nums;
        private int n;
        private int[] sumDp;

        public NumArray_210302_v2(int[] nums) {
            this.nums = nums;
            n = nums.length;

            sumDp = new int[n];
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    sumDp[i] = nums[i];
                } else {
                    sumDp[i] = nums[i] + sumDp[i - 1];
                }
            }
        }

        public int sumRange(int i, int j) {
            return sumDp[j] - sumDp[i] + nums[i];
        }
    }

    /**
     * 动态规划 + 前缀和
     */
    class NumArray {

        private int[] nums;
        private int n;
        private int[] preSumDp;

        public NumArray(int[] nums) {
            this.nums = nums;
            n = nums.length;

            preSumDp = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preSumDp[i + 1] = nums[i] + preSumDp[i];
            }
        }

        public int sumRange(int i, int j) {
            return preSumDp[j + 1] - preSumDp[i];
        }
    }
}
