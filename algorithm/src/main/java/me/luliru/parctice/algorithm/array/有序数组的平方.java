package me.luliru.parctice.algorithm.array;

/**
 * 有序数组的平方
 * Created by luliru on 2020/10/16.
 */
public class 有序数组的平方 {

    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int left = 0;
        int right = A.length-1;

        int index = A.length-1;
        while (left <= right) {
            int leftSqu = A[left] * A[left];
            int rightSqu = A[right] * A[right];
            if(leftSqu < rightSqu) {
                res[index] = rightSqu;
                right--;
            } else {
                res[index] = leftSqu;
                left++;
            }
            index--;
        }

        return res;
    }
}
