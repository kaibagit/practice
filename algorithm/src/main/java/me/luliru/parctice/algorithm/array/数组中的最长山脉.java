package me.luliru.parctice.algorithm.array;

/**
 * 数组中的最长山脉
 * Created by luliru on 2020/10/25.
 */
public class 数组中的最长山脉 {

    public static void main(String[] args) {
        new 数组中的最长山脉().longestMountain(new int[]{2,2,2});
    }

    /**
     * 贪心算法+双指针
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }

        int maxLength = 0;

        int start = 0;
        while (start < n - 1) {
            int point = start;
            // 上山
            while (point < n - 1 && A[point + 1] > A[point]) {
                point++;
            }
            int leftLength = point - start;     //不含山顶
            // 下山
            start = point;
            while (point < n - 1 && A[point + 1] < A[point]) {
                point++;
            }
            int rightLength = point - start;
            if (leftLength > 0 && rightLength > 0) {
                maxLength = Math.max(maxLength,leftLength + rightLength + 1);
            }

            // 下一轮
            // [2,2,2]这样的情况point就不会前进一步，所以要最少向前走一步
            start = Math.max(start + 1,point);
        }

        return maxLength;
    }


    /**
     * 动态规划
     * @param A
     * @return
     */
    public int longestMountain_dp(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }

        int[] leftDp = new int[n];
        int[] rightDp = new int[n];
        leftDp[0] = 0;
        rightDp[n-1] = 0;
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i-1]) {
                leftDp[i] = leftDp[i-1] + 1;
            } else {
                leftDp[i] = 0;
            }
        }
        for (int j = n-2; j >= 0; j--) {
            if (A[j] > A[j+1]) {
                rightDp[j] = rightDp[j+1] + 1;
            } else {
                rightDp[j] = 0;
            }
        }

        int maxLength = 0;
        for (int i = 1; i < n - 1; i++) {
            // 左右两侧必须要形成落差
            if (leftDp[i] > 0 && rightDp[i] > 0) {
                int length = leftDp[i] + rightDp[i] + 1;
                maxLength = Math.max(maxLength,length);
            }
        }

        return maxLength;
    }
}
