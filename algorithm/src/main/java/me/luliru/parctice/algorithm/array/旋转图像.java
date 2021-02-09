package me.luliru.parctice.algorithm.array;

/**
 * 旋转图像
 * Created by luliru on 2020/12/19.
 */
public class 旋转图像 {

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{4,8},{3,6}};
        int[][] matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
//        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        new 旋转图像().rotate_recurse(matrix);
    }

    /**
     * 数学方法：先转置再左右翻转
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 左右翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 -j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }

    /**
     * 递归多层循环
     *
     * @param matrix
     */
    public void rotate_recurse(int[][] matrix) {
        int n = matrix.length;
        recurse(matrix, 0, n);
    }

    public void recurse(int[][] matrix, int start, int n) {
        if (n <= 1) {
            return;
        }
        int end = start + n - 1;    // 包含
        for (int i = start; i < end; i++) {
            int tmp = matrix[start][i];
            // 上: [start][i]
            // 下: [end][end - (i - start)]
            // 左: [end - (i - start)][start]
            // 右: [i][end]

            // 上 <= 左
            matrix[start][i] = matrix[end - i + start][start];
            // 左 <= 下
            matrix[end - i + start][start] = matrix[end][end - i + start];
            // 下 <= 右
            matrix[end][end - i + start] = matrix[i][end];
            // 右 <= tmp
            matrix[i][end] = tmp;
        }
        recurse(matrix, start + 1, n - 2);
    }
}
