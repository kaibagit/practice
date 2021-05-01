package me.luliru.parctice.algorithm.binary_search;

/**
 * x的平方根
 * Created by luliru on 2021/2/14.
 */
public class LC69_x的平方根 {

    /**
     * 二分查找
     * @param x
     * @return
     */
    public int mySqrt_210306(int x) {
        int left = 0, right = x;
        while (left < right) {
            int mid = left + (right - left) >> 1;
            long product = (long)mid * mid;
            if (product == x) {
                return mid;
            } else if (product > x) {
                right = mid - 1;
            } else if (product < x) {
                left = mid + 1;
            }
        }
        return left - 1;
    }







































    /**
     * 二分法
     * @param x
     * @return
     */
    public int mySqrt_old(int x) {
        int left = 0;
        int right = x;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {    // 当x = 2147395599时会溢出
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
