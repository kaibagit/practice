package me.luliru.parctice.algorithm.binary_search;

/**
 * LC278_第一个错误的版本
 * Created by luliru on 2021/3/8.
 */
public class LC278_第一个错误的版本 {

    public int firstBadVersion_210308(int n) {
        int left = 1, right = n;    // 从0开始
        while (left < right) {      // 退出条件：left == right
            int mid = left + (right - left) / 2;
            boolean isBad = isBadVersion(mid);
            if (isBad) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isBadVersion(int version) {
        return false;
    }
}
