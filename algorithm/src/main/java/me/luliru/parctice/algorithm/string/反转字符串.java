package me.luliru.parctice.algorithm.string;

/**
 * 反转字符串
 * Created by luliru on 2020/9/25.
 */
public class 反转字符串 {

    public void reverseString(char[] s) {
        if(s.length == 0) {
            return;
        }
        char tmp = 0;
        for(int i=0;i<=(s.length-1)/2;i++) {
            tmp = s[s.length-1-i];
            s[s.length-1-i] = s[i];
            s[i] = tmp;
        }
    }
}
