package me.luliru.parctice.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

public class LC953_验证外星语词典 {

    public boolean isAlienSorted(String[] words, String order) {
        int m = words.length;   // 单词个数
        int n = 0;              // 所有单词的最大长度
        for (int i = 0; i < m; i++) {
            n = Math.max(n, words[i].length());
        }

        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            indexMap.put(order.charAt(i), i);
        }

        for (int col = 0; col < n; col++) {
            int preOrder = -1;
            for (int row = 0; row < m; row++) {
                String word = words[row];
                int currOrder = -1;
                if (col < word.length()) {
                    currOrder = indexMap.get(word.charAt(col));
                }

                if (preOrder > currOrder) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isAlienSorted2(String[] words, String order) {
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            indexMap.put(order.charAt(i), i);
        }

        int m = words.length;   // 单词个数
        String preWord = words[0];
        for (int row = 1; row < m; row++) {
            String currWord = words[row];

            int maxLength = Math.max(preWord.length(), currWord.length());
            for (int col = 0; col < maxLength; col++) {
                int preOrder = -1;
                int currOrder = -1;

                if (col < preWord.length()) {
                    preOrder = indexMap.get(preWord.charAt(col));
                }
                if (col < currWord.length()) {
                    currOrder = indexMap.get(currWord.charAt(col));
                }

                if (preOrder > currOrder) {
                    return false;
                }
                if (preOrder < currOrder) {
                    break;
                }
            }

            preWord = currWord;
        }

        return true;
    }
}
