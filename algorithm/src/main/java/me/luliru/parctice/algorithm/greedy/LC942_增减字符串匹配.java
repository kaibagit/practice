package me.luliru.parctice.algorithm.greedy;

import me.luliru.parctice.algorithm.PrintTool;

/**
 * LC942_增减字符串匹配
 * Created by luliru on 2022/5/9.
 */
public class LC942_增减字符串匹配 {

    public static void main(String[] args) {
        String s = "IDID";
        int[] result = new LC942_增减字符串匹配().diStringMatch(s);
        PrintTool.printArray(result);
    }

    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] perm = new int[n + 1];
        int low = 0;
        int high = n;
        // 双指针 + 贪心算法
        // 如果是I，则取最小值；如果是D，则取最大值；
        // 然后移动指针
        for (int i = 0; i < n; ++i) {
//            int a = 0;
            if (s.charAt(i) == 'I') {
                perm[i] = low;
                ++low;
            } else {
                perm[i] = high;
                --high;
            }
        }
        perm[n] = low;  // 最后low = high
        return perm;
    }
}
