package me.luliru.parctice.algorithm.dp;

/**
 * LC304_二维区域和检索
 * Created by luliru on 2021/3/2.
 */
public class LC304_二维区域和检索 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        new NumMatrix(matrix).sumRegion(2,1,4,3);
    }

    static class NumMatrix {

        private int[][] matrix;
        private int row;
        private int col;
        private int[][] sumDp;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            row = matrix.length;
            if (row > 0) {
                col = matrix[0].length;
            }

            sumDp = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int leftSum = 0, upperSum = 0, upperLeftSum = 0;
                    if (j > 0) {
                        leftSum = sumDp[i][j - 1];
                    }
                    if (i > 0) {
                        upperSum = sumDp[i - 1][j];
                    }
                    if (i > 0 && j > 0) {
                        upperLeftSum = sumDp[i - 1][j - 1];
                    }

                    sumDp[i][j] = leftSum + upperSum - upperLeftSum + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 < 0 || col1 < 0 || row2 > row - 1 || col2 > col - 1) {
                return 0;
            }

            int leftSum = 0, upperSum = 0, upperLeftSum = 0;
            if (col1 > 0) {
                leftSum = sumDp[row2][col1 - 1];
            }
            if (row1 > 0) {
                upperSum = sumDp[row1 - 1][col2];
            }
            if (row1 > 0 && col1 > 0) {
                upperLeftSum = sumDp[row1 -1][col1 - 1];
            }

            return sumDp[row2][col2] - leftSum - upperSum + upperLeftSum;
        }
    }
}
