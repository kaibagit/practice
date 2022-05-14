package me.luliru.parctice.algorithm;

/**
 * LC944_删列造序
 * Created by luliru on 2022/5/12.
 */
public class LC944_删列造序 {

    public int minDeletionSize(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();

        int delCols = 0;
        for (int col = 0; col < n; col++) {
            char pre = strs[0].charAt(col);
            for (int row = 1; row < m; row++) {
                char curr = strs[row].charAt(col);
                if (curr < pre) {
                    delCols++;
                    break;
                } else {
                    pre = curr;
                }
            }
        }

        return delCols;
    }
}
