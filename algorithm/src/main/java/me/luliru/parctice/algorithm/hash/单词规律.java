package me.luliru.parctice.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 单词规律
 * Created by luliru on 2020/12/16.
 */
public class 单词规律 {

    public static void main(String[] args) {
        new 单词规律().wordPattern("he", "unit");
        new 单词规律().wordPattern("abba", "dog cat cat dog");
    }

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> p2StrMap = new HashMap<>();
        Map<String, Character> str2PMap = new HashMap<>();
        int pidx = 0;
        int sidx = 0;
        for (; pidx < pattern.length(); pidx++) {
            Character p = pattern.charAt(pidx);
            int wordStart = sidx;
            int wordEnd = sidx;
            while (sidx < s.length()) {
                char c = s.charAt(sidx);
                sidx++;
                if (c == ' ') {
                    wordEnd = sidx-1;
                    break;
                } else {
                    wordEnd = sidx;
                }
            }
            // 说明s字符串已经没有更多字符了
            if (wordStart == wordEnd) {
                return false;
            }
            String word = s.substring(wordStart, wordEnd);
            Character existedP = str2PMap.get(word);
            if (existedP != null && existedP != p) {
                return false;
            }
            String existedStr = p2StrMap.get(p);
            if (existedStr != null && !existedStr.equals(word)) {
                return false;
            }
            str2PMap.put(word, p);
            p2StrMap.put(p, word);
        }

        return pidx == pattern.length() && sidx == s.length();
    }
}
