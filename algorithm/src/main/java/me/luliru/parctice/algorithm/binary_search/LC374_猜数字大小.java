package me.luliru.parctice.algorithm.binary_search;

/**
 * LC374_猜数字大小
 * Created by luliru on 2021/3/8.
 */
public class LC374_猜数字大小 {

    /**
     * 二分查找
     * @param n
     * @return
     */
    public int guessNumber_210308(int n) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = guess(mid);
            if (result < 0) {
                right = mid - 1;
            } else if (result == 0) {
                return mid;
            } else if (result > 0) {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int guess(int num) {
        return 0;
    }
}
