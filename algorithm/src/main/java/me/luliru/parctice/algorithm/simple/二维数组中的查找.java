package me.luliru.parctice.algorithm.simple;

/**
 * 二维数组中的查找
 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。

 Consider the following matrix:
 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]

 Given target = 5, return true.
 Given target = 20, return false.

 解题思路

 要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。

 该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，当前元素的查找区间为左下角的所有元素。
 * Created by luliru on 2020/8/8.
 */
public class 二维数组中的查找 {

    public static void main(String[] args) {
        int[][] array = new int[5][5];
        array[0] = new int[]{1, 2, 3, 10, 18};
        array[1] = new int[]{4,   5,  6, 13, 21};
        array[2] = new int[]{7,   8,  9, 14, 23};
        array[3] = new int[]{11, 12, 16, 17, 26};
        array[4] = new int[]{15, 19, 22, 24, 30};
        System.out.println(mineFind(5,array));
        System.out.println(mineFind(20,array));
    }

    public static boolean mineFind(int target, int [][] array){
        int xLength = array.length;
        if(xLength == 0) {
            return false;
        }
        int yLength = array[0].length;
        int i = xLength -1;
        int j = 0;
        while (i >=0 && j <yLength) {
            int val = array[i][j];
            if(val == target){
                return true;
            }else if(target < val) {
                i--;
            } else{
                j++;
            }
        }
        return false;
    }
}
