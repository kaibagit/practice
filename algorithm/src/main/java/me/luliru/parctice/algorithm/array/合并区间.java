package me.luliru.parctice.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * Created by luliru on 2020/10/15.
 */
public class 合并区间 {

    /**
     * 先进行排序，然后把前一区间right值和后一区间left值作比较，判断是否需要合并
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> resList = new ArrayList<>();
        int[] lastInterval = intervals[0];      // 上一个区间
        for(int i=1;i<intervals.length;i++) {
            int[] curr = intervals[i];
            int right_1 = lastInterval[1];
            int left_2 = curr[0];
            if(right_1 < left_2) {   //前一个区间右数字比后一个区间小，且不连续，则无法合并
                resList.add(lastInterval);
                lastInterval = curr;
            } else {    // 如果存在重叠，则合并生成一个新区间
                int newRight = Math.max(lastInterval[1],curr[1]);
                int[] newInterval = new int[]{lastInterval[0],newRight};
                lastInterval = newInterval;
            }
        }
        // 将最后一个区间纳入结果
        resList.add(lastInterval);

        int[][] res = new int[resList.size()][2];
        for(int i =0;i<resList.size();i++) {
            res[i] = resList.get(i);
        }

        return res;
    }
}
