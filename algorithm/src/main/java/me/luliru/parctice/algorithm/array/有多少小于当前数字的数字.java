package me.luliru.parctice.algorithm.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 有多少小于当前数字的数字
 * Created by luliru on 2020/10/26.
 */
public class 有多少小于当前数字的数字 {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            data[i][0] = nums[i];
            data[i][1] = i;
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] res = new int[n];
        int lastNum = -1;
        int lessCount = 0;
        for (int i = 0; i < n; i++) {
            if (data[i][0] > lastNum) {
                lessCount = i;
                lastNum = data[i][0];
                res[data[i][1]] = lessCount;
            }
            if (data[i][0] == lastNum) {
                res[data[i][1]] = lessCount;
            }
        }

        return res;
    }


}
