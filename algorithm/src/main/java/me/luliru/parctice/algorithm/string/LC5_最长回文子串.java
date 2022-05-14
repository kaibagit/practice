package me.luliru.parctice.algorithm.string;

/**
 * 最长回文子串
 * Created by luliru on 2020/10/18.
 */
public class LC5_最长回文子串 {

    public static void main(String[] args) {
//        System.out.println(new LC5_最长回文子串().longestPalindrome_210307_v2("aba"));
        System.out.println(new LC5_最长回文子串().longestPalindrome_210307_v2("aaaa"));
    }


    /**
     * 暴力求解
     * @param s
     * @return
     */
    public String longestPalindrome_baoli(String s) {
        int beginOfLongest = 0;
        int endOfLongest = 0;
        int longest = 0;

        for (int begin = 0; begin < s.length(); begin++) {
            for (int end = begin; end < s.length(); end++) {
                if (isPalindrome(s, begin, end)) {
                    int len = end - begin + 1;
                    if (len > longest) {
                        longest = len;
                        beginOfLongest = begin;
                        endOfLongest = end;
                    }
                }
            }
        }

        return s.substring(beginOfLongest, endOfLongest + 1);
    }
    private boolean isPalindrome(String s, int begin, int end) {
        while (begin < end) {
            if (s.charAt(begin) != s.charAt(end)) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }

    /**
     * 中心扩散法
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();

        int longest = 1;
        int start = 0;

        for (int i = 0; i < n - 1; i++) {   //第0位和末尾，无法再往外扩散，所以取[1,n-1)，但是考虑到[b,b]的情况，所以范围应该为[0,n-1)
            // 奇数长度回文
            int left = leftAboundary(s, i, i);
            // 左边不包含中心点长度：i - left，总长度为 2 * (i - left) + 1，即2 * i - 2 * left + 1
            int len = 2 * i - 2 * left + 1;
            if (len > longest) {
                start = left;
                longest = len;
            }

            // 偶数长度回文
            left = leftAboundary(s, i, i + 1);
            // 左边包含中心点长度：i - left + 1，总长度为 2 * (i - left + 1)，即2 * i - 2 * left + 2
            len = 2 * i - 2 * left + 2;
            if (len > longest) {
                start = left;
                longest = len;
            }
        }

        return s.substring(start, start + longest);
    }
    private int leftAboundary(String s, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && r < s.length()) {  // 退出循环条件，l、r越界，或者l、r的两个字符不相等
            if (s.charAt(l) != s.charAt(r)) {
                break;
            }

            l--;
            r++;
        }

        // 符合条件的左边界为 l + 1，符合条件的右边界为 r - 1
        return l + 1;
    }

    /**
     * 中心扩散法
     * @param s
     * @return
     */
    public String longestPalindrome_my(String s) {
        int n = s.length();

        int beginOfLongest = 0;
        int endOfLongest = 0;
        int longest = 1;

        for (int i = 0; i < n; i++) {
            int next = i + 1;
            // 中心点为2个字符
            if (next < n && s.charAt(i) == s.charAt(next)) {
                if (longest == 1) {
                    beginOfLongest = i;
                    endOfLongest = next;
                    longest = 2;
                }

                int left = i - 1;
                int right = next + 1;
                while (left >= 0 && right < n) {
                    if (s.charAt(left) != s.charAt(right)) {
                        break;
                    }

                    int len = right - left + 1;
                    if (len > longest) {
                        longest = len;
                        beginOfLongest = left;
                        endOfLongest = right;
                    }

                    left--;
                    right++;
                }
            }

            // 中心点为1个字符
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < n) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }

                int len = right - left + 1;
                if (len > longest) {
                    longest = len;
                    beginOfLongest = left;
                    endOfLongest = right;
                }

                left--;
                right++;
            }
        }

        return s.substring(beginOfLongest, endOfLongest + 1);
    }

















    /**
     * 暴力法
     * @param s
     * @return
     */
    public String longestPalindrome_210307_v1(String s) {
        int start = 0, end = 0;     // end 包含
        for (int left = 0; left < s.length(); left++) {
            for (int right = left; right < s.length(); right++) {   // right 包含
                if (validate(s, left, right) && end - start < right - left) {
                    start = left;
                    end = right;
                }
            }
        }
        return s.substring(start, end + 1);
    }
    private boolean validate(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    /**
     * 动态规划
     * @param s
     * @return
     */
    public String longestPalindrome_210307_v2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];     // dp[i][j]代表从i到j是否为回文
        int start = 0, end = 0;     // 包含end
        // 由状态转移返程：dp[i][j] = s[i]==s[j] && dp[i+1][j-1]可知，当前[j][j]会转移到它的左下角，横向遍历会有问题，需要纵向遍历
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 只有1个字符、2个字符的时候，不需要状态转移
                    if (i == j || i + 1 == j || dp[i + 1][j - 1]) {     // 只有一个字符 || 只有2个字符 || 大于2个字符
                        dp[i][j] = true;
                        if (end - start < j - i) {
                            start = i;
                            end = j;
                        }
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 中心扩展法
     * @param s
     * @return
     */
    public String longestPalindrome_210307_v3(String s) {
        int n = s.length();
        int longest = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            // 奇数长度时
            int left = i - 1, right = i + 1;
            while (left >= 0 && right < n) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                --left;
                ++right;
            }
            //  此时，left = 符合条件left - 1， right = 符合条件 + 1
            if (right - left + 1 - 2 > longest) {
                longest = right - left + 1 - 2;
                start = left + 1;
            }

            // 偶数长度时
            left = i;
            right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            if (right - left + 1 - 2 > longest) {
                longest = right - left + 1 - 2;
                start = left + 1;
            }
        }
        return s.substring(start, start + longest);
    }






















    int maxLength = 1;
    int gLeft = -1;
    int gRight = 1;

    public String longestPalindrome_old(String s) {
        if(s.length() == 0) {
            return "";
        }
        if(s.length() == 1) {
            return s;
        }
//        if(s.length() == 2) {
//            if(s.charAt(0) == s.charAt(1)) {
//                return s;
//            }else{
//                return s.substring(0,1);
//            }
//        }

        // 从1开始遍历到n
        for(int i=1;i<s.length();i++){
            // 如果回文长度为奇数，则一直到left-1 == right+1，且没有越界，则记录left和right，并记录长度
            odd(s,i);
            // 如果回文长度为偶数
            even(s,i);
        }
        return s.substring(gLeft+1,gRight);
    }

    private void odd(String s,int i){
        int left = i-1;
        int right = i+1;
        // 回文区间为(left,right)，长度为right-left-1，最小为1，边界：left = -1 || right = n
        while (left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            left = left-1;
            right = right+1;
            int newLength = right-left-1;
            if(newLength > maxLength) {
                gLeft = left;
                gRight = right;
                maxLength = newLength;
            }
        }
    }

    private void even(String s,int i) {
        int left = i-1;
        int right = i;
        // 回文区间为(left,right)，长度为right-left-1，最小为0，边界：left = -1 || right = n
        while (left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            left = left-1;
            right = right+1;
            int newLength = right-left-1;
            if(newLength > maxLength) {
                gLeft = left;
                gRight = right;
                maxLength = newLength;
            }
        }
    }
}
