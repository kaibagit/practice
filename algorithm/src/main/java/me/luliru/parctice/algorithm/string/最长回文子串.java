package me.luliru.parctice.algorithm.string;

/**
 * 最长回文子串
 * Created by luliru on 2020/10/18.
 */
public class 最长回文子串 {

    public static void main(String[] args) {
        System.out.println(new 最长回文子串().longestPalindrome("ac"));
    }

    int maxLength = 1;
    int gLeft = -1;
    int gRight = 1;

    public String longestPalindrome(String s) {
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
