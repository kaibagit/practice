package me.luliru.parctice.algorithm.bit;

/**
 * 找不同
 * Created by luliru on 2020/12/18.
 */
public class 找不同 {

    public static void main(String[] args) {

    }

    public char findTheDifference(String s, String t) {
        char res = 0;
        for (int i = 0; i < s.length(); i++) {
            res ^= s.charAt(i);
        }
        for (int i = 0; i< t.length(); i++) {
            res ^= t.charAt(i);
        }
        return res;
    }
}
