//package me.luliru.parctice.algorithm.dp;
//
//import java.util.Arrays;
//import java.util.Comparator;
//
///**
// * LC354_俄罗斯套娃信封问题
// * Created by luliru on 2021/3/3.
// */
//public class LC354_俄罗斯套娃信封问题 {
//
//
//    /**
//     * 单调递增数组 + 二分查找
//     * @param envelopes
//     * @return
//     */
//    public int maxEnvelopes_210313(int[][] envelopes) {
//        int n = envelopes.length;
//        if (n < 2) {
//            return n;
//        }
//
//        // 为了让套数尽可能多，先进行排序
//        Arrays.sort(envelopes, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                // 宽度从小到大排列，如果宽度相同，则采用贪心策略，让宽度小的尽可能排后面，可以多套几层
//                if (o1[0] == o2[0]) {
//                    return o1[1] - o2[1];
//                }
//                return o1[0] - o2[0];
//            }
//        });
//
//        int max = 1;
//        int[][] smallArr = new int[n + 1][2];
//        smallArr[0] = envelopes[0];
//        for (int i = 1; i < n; i++) {
//            int[] curr = envelopes[i];
//            if (curr[1] > smallArr[max][1])
//            int left = 1, right = max;
//            // 找到第一个比smallArr宽度大的位置
//            while (left < right) {
//
//            }
//        }
//
//        return max;
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    /**
//     * 动态规划
//     * @param envelopes
//     * @return
//     */
//    public int maxEnvelopes_old(int[][] envelopes) {
//        int n = envelopes.length;
//        if (n < 2) {
//            return n;
//        }
//
//        // 为了让套数尽可能多，先进行排序
//        Arrays.sort(envelopes, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                // 宽度从小到大排列，如果宽度相同，则采用贪心策略，让宽度小的尽可能排后面，可以多套几层
//                if (o1[0] == o2[0]) {
//                    return o1[1] - o2[1];
//                }
//                return o1[0] - o2[0];
//            }
//        });
//
//        int ans = 1;
//        int[] dp = new int[n];
//        dp[0] = 1;
//        for (int i = 1; i < n; i++) {
//            int maxTier = 1;
//            for (int j = 0; j < i; j++) {
//                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
//                    maxTier = Math.max(dp[j] + 1, maxTier);
//                }
//            }
//            dp[i] = maxTier;
//            ans = Math.max(maxTier, ans);
//        }
//
//        return ans;
//    }
//}
