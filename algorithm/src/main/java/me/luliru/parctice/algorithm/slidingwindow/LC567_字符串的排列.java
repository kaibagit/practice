package me.luliru.parctice.algorithm.slidingwindow;

import java.util.Arrays;

/**
 * LC567_字符串的排列
 * Created by luliru on 2021/2/18.
 */
public class LC567_字符串的排列 {

    public static void main(String[] args) {
        new LC567_字符串的排列().checkInclusion_v2("ab", "eidboaoo");
    }

    /**
     * 滑动窗口+字典
     * 时间复杂度：O(n)
     * 空间复杂度：O(26)
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion_v1(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        int[] s1Counts = new int[26];
        // 统计s1每个字母的个数
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            s1Counts[c - 'a']++;
        }

        // 滑动窗口，确认s2是否符合s1的个数要求
        // 初始化前s1.length个元素的字典
        int[] s2Counts = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char c = s2.charAt(i);
            s2Counts[c - 'a']++;
        }
        // 比对前s1.length个元素字符是否符合
        if (Arrays.equals(s1Counts, s2Counts)) {
            return true;
        }
        // 滑动窗口依次向右滑动，然后比对
        for (int left = 1, right = s1.length(); right < s2.length(); left++, right++) {     // 滑动窗口为[left, right]
            s2Counts[s2.charAt(left - 1) - 'a']--;
            s2Counts[s2.charAt(right) - 'a']++;
            if (Arrays.equals(s1Counts, s2Counts)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 滑动窗口+字典，优化
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion_v2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (m < n) {
            return false;
        }
        int[] diffDict = new int[26];
        // 统计s1与s2前n个字母的差异
        for (int i = 0; i < n; i++) {
            diffDict[s1.charAt(i) - 'a']--;     // 这里从s2-s1的视角看
            diffDict[s2.charAt(i) - 'a']++;
        }

        int diffCounts = 0;
        for (int i = 0; i < diffDict.length; i++) {
            if (diffDict[i] != 0) {
                diffCounts++;
            }
        }
        if (diffCounts == 0) {
            return true;
        }

        // 滑动窗口依次向右滑动，然后比对
        for (int left = 1, right = n; right < m; left++, right++) {     // 滑动窗口为[left, right]
            char x = s2.charAt(left - 1), y = s2.charAt(right);  // x出，y进
            if (x == y) {
                continue;
            }
            // 处理x移除窗口
            if (diffDict[x - 'a'] == 0) {
                diffCounts++;
            }
            diffDict[x - 'a']--;
            if (diffDict[x - 'a'] == 0) {
                diffCounts--;
            }
            // 处理y移入窗口
            if (diffDict[y - 'a'] == 0) {
                diffCounts++;
            }
            diffDict[y - 'a']++;
            if (diffDict[y - 'a'] == 0) {
                diffCounts--;
            }

            if (diffCounts == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 双指针，动态滑动窗口
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion_v3(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (m < n) {
            return false;
        }
        int[] letterDict = new int[26];
        for (int i = 0; i < n; i++) {
            letterDict[s1.charAt(i) - 'a']--;
        }

        int left = 0;
        for (int right = 0; right < m; right++) {
            int x = s2.charAt(right) - 'a';
            letterDict[x]++;
            while (letterDict[x] > 0) {     // 大于0说明，该窗口中出现了多余s1的字符；否则的话，继续执行循环，相当于滑动窗口 + 1
                letterDict[s2.charAt(left) - 'a']--;
                left++;       // left一直右移，直到left = right + 1，将right的计数抹平
            }
            if (right - left + 1 == n) {    // 这里窗口的范围为[left, right]，如果窗口的大小为n，且每个字典值为0，则符合条件了
                return true;
            }
        }

        return false;
    }
}
