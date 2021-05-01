package me.luliru.parctice.algorithm.binary_search;

/**
 * LC74_搜索二维矩阵
 * Created by luliru on 2021/3/8.
 */
public class LC74_搜索二维矩阵 {


    /**
     * 二分查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix_210330_v1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int[] midPosition = convertPosition(mid, m, n);
            int row = midPosition[0];
            int col = midPosition[1];
            if (target < matrix[row][col]) {
                right = mid - 1;
            } else if (target == matrix[row][col]) {
                return true;
            } else if (target > matrix[row][col]) {
                left = mid + 1;
            }
        }
        return false;
    }

    private int[] convertPosition(int i, int m, int n) {
        int row = i / n;
        int col = i % n;
        return new int[]{row, col};
    }

    /**
     * 两次二分查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix_210330_v2(int[][] matrix, int target) {
        int m = matrix.length;
        // 1、查找可能所在的行，即查找最后一个<=target
        int top = 0, bottom = m - 1;
        while (top < bottom) {      // 退出条件：top = bottom
            int mid = top + (bottom - top + 1) / 2;
            if (target < matrix[mid][0]) {
                bottom = mid - 1;
            } else if (target == matrix[mid][0]) {
                return true;
            } else if (target > matrix[mid][0]) {
                top = mid;
            }
        }

        // 2、在该行中查找可能的值
        int left = 0, right = matrix[top].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < matrix[top][mid]) {
                right = mid - 1;
            } else if (target == matrix[top][mid]) {
                return true;
            } else if (target > matrix[top][mid]) {
                left = mid + 1;
            }
        }
        return false;
    }






































    /**
     * 二分查找，把二维数组抽象成一个虚拟的一维数组
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix_210308(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int[] p = getPosition(m, n, mid);
            int row = p[0], col = p[1];
            if (target < matrix[row][col]) {
                right = mid - 1;
            } else if (target == matrix[row][col]) {
                return true;
            } else if (target > matrix[row][col]) {
                left = mid + 1;
            }
        }
        return false;
    }

    private int[] getPosition(int m, int n, int index) {
        int col = index % n;
        int row = index / n;
        return new int[]{row, col};
    }
}
