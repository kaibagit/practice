package me.luliru.parctice.algorithm.greedy;

/**
 * LC409_最长回文串
 * Created by luliru on 2022/5/9.
 */
public class LC409_最长回文串 {

    public int longestPalindrome(String s) {
        int n = 58; // 由于Z和a之间的ascii码不是连续的，所以要延长
        int[] counts = new int[n];
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int index = c - 'A';
            ++counts[index];
        }

        int sum = 0;
        int single = 0;     // 是否含有奇数个字符
        for (int i = 0; i < n; ++i) {
            int count = counts[i];
            if (count == 0) {
                continue;
            }
            if ((count & 1) == 1) {
                // 奇数，存在大于1的情况
                sum = sum + (count / 2) * 2;
                single = 1;
            } else {
                // 偶数
                sum = sum + count;
            }
        }

        return sum + single;
    }
}
