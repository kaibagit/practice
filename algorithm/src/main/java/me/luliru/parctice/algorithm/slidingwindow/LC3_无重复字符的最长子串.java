package me.luliru.parctice.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * Created by luliru on 2020/10/15.
 */
public class LC3_无重复字符的最长子串 {

    public static void main(String[] args) {
        new LC3_无重复字符的最长子串().lengthOfLongestSubstring_210218_v1("abcabcbb");
    }

    /**
     * 暴力求解，往前遍历
     * 时间：o(n^2)，空间：O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_210217_v1(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int maxLength = 1;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            for (int j = i - 1; j >= 0; j--) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                maxLength = Math.max(i - j + 1, maxLength);
                set.add(s.charAt(j));
            }
        }
        return maxLength;
    }


    /**
     * 暴力求解，往后遍历+hashset
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_v1(String s) {
        int max = 0;
        int count = 0;
        Set<Character> set = new HashSet<>();
        for(int i=0;i<s.length();i++) {
            // 依次往后遍历，查找最长不重复的长度
            for(int j=i;j<s.length();j++) {
                Character c = s.charAt(j);
                if(set.add(c)) {
                    count ++;
                } else {    // 发现重复，则中断，并计算长度
                    max = max<count?count:max;
                    count = 0;
                    break;
                }
            }
            // 处理遍历到最后一个元素的长度计算
            max = max<count?count:max;

            // 重置，开始下一次迭代
            count = 0;
            set.clear();
        }
        return max;
    }


    /**
     * 动态规划
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_210218_v1(String s) {
        // dp[i]表示以s[i]结尾的字符串中最长的不包含重复字符的子字符串的长度
        // 状态初始化: dp[0] = 1
        // 状态转移方程:
        // 假设当前字符s[i]为x,找出x在0到(i-1)中最近的位置为index
        // 若index < 0，则dp[i] = dp[i-1] + 1
        // 若index > 0，且 i - index > dp[i-1]，说明s[index]在子字符串dp[i-1]之外,则dp[i] = dp[i-1] + 1
        // 若i - index <= dp[i-1] ，说明s[index]在子字符串dp[i-1]之内，则dp[i] = i - index;
        if (s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        Map<Character, Integer> charIndexMap = new HashMap<>();

        // 初始化元素0
        dp[0] = 1;
        charIndexMap.put(s.charAt(0), 0);
        int maxLength = dp[0];

        for (int i = 1; i < s.length(); i++) {
            Integer index = charIndexMap.get(s.charAt(i));
            if (index != null && i - index <= dp[i - 1]) {
                dp[i] = i - index;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
            charIndexMap.put(s.charAt(i), i);
            maxLength = Math.max(dp[i], maxLength);
        }

        return maxLength;
    }



    /**
     * 滑动串口：如果没重复，则窗口right指针移动；如果重复，则窗口left指针移动到上次重复位置后面
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_v3(String s) {
        int maxLength = 0;
        int start = -1;     //滑动窗口的开始位置，滑动窗口为(start,i]
        Map<Character, Integer/*最后一次出现的索引值*/> lastIndexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer lastIndex = lastIndexMap.get(c);
            // 从未出现过，或者前一个索引值已失效，则扩大窗口
            if (lastIndex == null || start > lastIndex) {
                int length = i - start;
                maxLength = Math.max(maxLength, length);
            } else {     // 出现过，且在窗口内，则缩小窗口
                start = lastIndex;
            }
            lastIndexMap.put(c, i);
        }

        return maxLength;
    }




























    /**
     * 自己实现的滑动串口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_v2(String s) {
        int maxLength = 0;
        int start = -1; // 滑动窗口大小为(start,i]
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer lastIndex = lastIndexMap.get(s.charAt(i));
            if (lastIndex == null || lastIndex <= start) {
                maxLength = Math.max(i - start, maxLength);
            } else {
                start = lastIndex;
            }
            lastIndexMap.put(s.charAt(i), i);
        }
        return maxLength;
    }
















}
