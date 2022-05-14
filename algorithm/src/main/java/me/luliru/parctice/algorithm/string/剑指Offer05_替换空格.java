package me.luliru.parctice.algorithm.string;

/**
 * 剑指Offer05_替换空格
 * Created by luliru on 2022/5/12.
 */
public class 剑指Offer05_替换空格 {

    public String replaceSpace(String s) {
        int len = s.length();
        int spaces = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') spaces++;
        }

        int newLen = len + 2 * spaces;
        char[] newStr = new char[newLen];
        int srcPoint = len - 1;
        int distPoint = newLen - 1;
        while (srcPoint >= 0) {
            char c = s.charAt(srcPoint);
            if (c == ' ') {
                newStr[distPoint - 2] = '%';
                newStr[distPoint - 1] = '2';
                newStr[distPoint] = '0';
                distPoint -= 3;
            } else {
                newStr[distPoint] = c;
                distPoint--;
            }

            srcPoint--;
        }

        return new String(newStr);
    }
}
