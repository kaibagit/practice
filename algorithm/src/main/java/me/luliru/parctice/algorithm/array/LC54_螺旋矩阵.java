package me.luliru.parctice.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * Created by luliru on 2020/10/19.
 */
public class LC54_螺旋矩阵 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{new int[]{1,2,3,4},new int[]{5,6,7,8},new int[]{9,10,11,12}};
        new LC54_螺旋矩阵().spiralOrder_2103015(matrix);

    }


    /**
     * 模拟 + 迭代
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder_2103015(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> output = new ArrayList<>(m * n);
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            // 输出顶部元素
            for (int col = left; col <= right; col++) {
                output.add(matrix[top][col]);
            }

            // 输出右侧元素
            for (int row = top + 1; row <= bottom; row++) {
                output.add(matrix[row][right]);
            }

            // 输出底部元素
            // 当只有一行时，top = bottom，会导致重复添加元素
            for (int col = right - 1; col >= left && top < bottom; col--) {
                output.add(matrix[bottom][col]);
            }

            // 输出左侧元素
            // 当只有一列时，left = right，会导致重复添加元素
            for (int row = bottom - 1; row >= top + 1 && left < right; row--) {
                output.add(matrix[row][left]);
            }

            // 下一轮迭代
            ++left;
            --right;
            ++top;
            --bottom;
        }

        return output;
    }










































    public List<Integer> spiralOrder_old(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        loop(res,matrix,0,n-1,0,m-1);
        return res;
    }

    private void loop(List<Integer> res,int[][] matrix,int left,int right,int top,int bottom) {
        for (int i=left; i<=right; i++) {
            res.add(matrix[top][left]);
        }
        for (int i=top+1; i<=bottom; i++) {
            res.add(matrix[i][right]);
        }
        for (int i=right-1; i>=left; i--) {
            res.add(matrix[bottom][i]);
        }
        for (int i=bottom-1; i>=top+1;i--) {
            res.add(matrix[i][left]);
        }
        loop(res,matrix,left+1,right-1,top+1,bottom-1);
    }
}
