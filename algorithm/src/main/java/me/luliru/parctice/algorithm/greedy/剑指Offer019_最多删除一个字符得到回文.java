package me.luliru.parctice.algorithm.greedy;

/**
 * 剑指Offer019_最多删除一个字符得到回文
 * Created by luliru on 2022/5/9.
 */
public class 剑指Offer019_最多删除一个字符得到回文 {

    public boolean validPalindrome(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low < high) {   // 如果是偶数，可能会使high = low - 1
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
                continue;
            } else {
                // 不是回文，则跳过一个字符
                // 1、跳过low的字符
                // 2、跳过high的字符
                return validPalindrome(s, low + 1, high) || validPalindrome(s, low, high - 1);
            }
        }
        return true;
    }

    private boolean validPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
