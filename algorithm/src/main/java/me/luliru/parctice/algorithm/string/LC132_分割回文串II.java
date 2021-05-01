//package me.luliru.parctice.algorithm.string;
//
///**
// * LC132_分割回文串II
// * Created by luliru on 2021/3/8.
// */
//public class LC132_分割回文串II {
//
//    /**
//     * 动态规划
//     * @param s
//     * @return
//     */
//    public int minCut_210308(String s) {
//        // TODO 特殊输入
//        int n = s.length();
//        // 初始化是否回文校验，validateDp[i][j]表示s[i,j]是否为回文
//        boolean[][] validateDp = new boolean[n][n];
//        for (int j = 0; j < n; j++) {
//            for (int i = 0; i <= j; i++) {
//                if (s.charAt(i) == s.charAt(j)) {
//                    if (i == j || i + 1 == j || validateDp[i + 1][j - 1]) {
//                        validateDp[i][j] = true;
//                    }
//                }
//            }
//        }
//
//        int[] minCutDp = new int[n];
//        for (int i = 0; i < n; i++) {
//            int minCut = n - 1;
//            for (int j = 1; j < i; j++) {
//                minCut = Math.min(minCut[])
//            }
//        }
//    }
//}
