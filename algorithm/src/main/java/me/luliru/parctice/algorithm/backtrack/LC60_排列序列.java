package me.luliru.parctice.algorithm.backtrack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LC60_排列序列
 * Created by luliru on 2021/2/23.
 */
public class LC60_排列序列 {

    public static void main(String[] args) {
        new LC60_排列序列().getPermutation(3, 3);
//        new LC60_排列序列().getPermutation(4, 9);
    }

    private int count;

    /**
     * 回溯暴力破解
     * @param n
     * @param k
     * @return
     */
    public String getPermutation_v1(int n, int k) {
        return dfs(n, k, new LinkedList<>(), new boolean[n + 1]);
    }

    private String dfs(int n, int k, Deque<Integer> path,boolean[] used) {
        if (path.size() == n) {
            ++count;
            if (count == k) {
                StringBuilder builder = new StringBuilder(n);
                while (!path.isEmpty()) {
                    builder.append(path.pollFirst());
                }
                return builder.toString();
            }
        }
        // 终止条件
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }

            // 做选择
            path.addLast(i);
            used[i] = true;
            String result = dfs(n, k, path,used);
            if (result != null) {
                return result;
            }

            // 撤销选择
            path.removeLast();
            used[i] = false;
        }
        return null;
    }

    /**
     * 数学方式
     * @param n
     * @param k
     * @return
     */
    public String getPermutation_v2(int n, int k) {
        StringBuilder ans = new StringBuilder();
        int m = n - 1, remain = k - 1;      // k这里，因为后面除法，是从0开始的，而我们在后面多加了一个1，多以这里需要扣掉
        boolean[] used = new boolean[n + 1];
        while (remain > 0) {
            int factorial = factorial(m);
            int firstNumSeq = remain / factorial + 1;
            int firstNum = 0;
            while (firstNumSeq > 0) {
                ++firstNum;
                if (!used[firstNum]) {
                    --firstNumSeq;
                }

            }
            ans.append(firstNum);
            used[firstNum] = true;

            // 继续下一轮
            --m;
            remain = remain % factorial;
        }
        // 前面顺序已确定，接下来按照顺序，把剩余数字补齐
        for (int i = 1; i <= n; i++) {
            if(!used[i]) {
                ans.append(i);
            }
        }
        return ans.toString();
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    /**
     * 数学方法，按照官方思路改写的版本
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        StringBuilder ans = new StringBuilder();
        int m = n - 1, remain = k - 1;      // k这里，因为后面除法，是从0开始的，而我们在后面多加了一个1，多以这里需要扣掉
        boolean[] used = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int firstNumSeq = remain / factorial[m] + 1;
            int firstNum = 0;
            while (firstNumSeq > 0) {
                ++firstNum;
                if (!used[firstNum]) {
                    --firstNumSeq;
                }

            }
            ans.append(firstNum);
            used[firstNum] = true;

            // 继续下一轮
            remain = remain % factorial[m];
            --m;
        }
        return ans.toString();
    }
}
