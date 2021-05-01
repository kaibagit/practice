package me.luliru.parctice.algorithm.array;

/**
 * LC896_单调数列
 * Created by luliru on 2021/3/2.
 */
public class LC896_单调数列 {

    public boolean isMonotonic(int[] A) {
        if (A.length <= 1) {
            return true;
        }
        Boolean increase = null;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                if (increase != null && increase) {
                    return false;
                }
                increase = false;
            }
            if (A[i] > A[i - 1]) {
                if (increase != null && !increase) {
                    return false;
                }
                increase = true;
            }
        }

        return true;
    }
}
