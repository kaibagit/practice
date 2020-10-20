package me.luliru.parctice.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * Created by luliru on 2020/10/19.
 */
public class 螺旋矩阵 {

    public List<Integer> spiralOrder(int[][] matrix) {
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
