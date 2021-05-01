package me.luliru.parctice.algorithm.dp;

/**
 * LC221_最大正方形
 * Created by luliru on 2021/3/2.
 */
public class LC221_最大正方形 {

    /**
     * 个人：动态规划
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length , n = matrix[0].length;
        int maxSide = 0;
        int[][] sides = new int[m][n];      // 以matrix[i][j]为右下角的正方形边长
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    sides[i][j] = 0;
                    continue;
                }

                int exceptSide = 1;
                if (i - 1 < 0 || j - 1 < 0) {   // 在matrix最外围一圈
                    exceptSide = 1;
                } else if (sides[i - 1][j - 1] == 0) {  // 左上角是0，则正方形最大边长为1
                    exceptSide = 1;
                } else {
                    int side = sides[i - 1][j - 1];
                    // 分别往左、往上校验，是否这side长度的值都为'1'，如果都为1，则相当于比左上角边长+1，否则去较短的边长作为该正方形边长
                    for (int row = i - 1, col = j - 1; row >= i - side; row--, col--) {
                        if (matrix[row][j] == '1' && matrix[i][col] == '1') {
                            exceptSide = i - row + 1;
                        } else {
                            break;
                        }
                    }
                }
                sides[i][j] = exceptSide;
                maxSide = Math.max(exceptSide, maxSide);
            }
        }

        return maxSide * maxSide;
    }
}
