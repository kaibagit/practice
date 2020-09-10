package me.luliru.parctice.algorithm.simple;

import java.util.Stack;

/**
 * 反转字符串中的单词III
 *
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

 示例：

 输入："Let's take LeetCode contest"
 输出："s'teL ekat edoCteeL tsetnoc"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by luliru on 2020/8/30.
 */
public class 反转字符串中的单词III {

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(mine_everseWords(s));
    }

    public static String mine_everseWords(String s) {
        StringBuilder builder = new StringBuilder(s.length());
        Stack stack = new Stack();
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c == ' ') {
                while(!stack.empty()) {
                    builder.append(stack.pop());
                }
                builder.append(c);
            } else {
                stack.push(c);
            }
        }
        while(!stack.empty()) {
            builder.append(stack.pop());
        }
        return builder.toString();
    }

    /**
     * 开辟一个新字符串。然后从头到尾遍历原字符串，直到找到空格为止，此时找到了一个单词，并能得到单词的起止位置。随后，根据单词的起止位置，可以将该单词逆序放到新字符串当中。如此循环多次，直到遍历完原字符串，就能得到翻转后的结果。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/solution/fan-zhuan-zi-fu-chuan-zhong-de-dan-ci-iii-by-lee-2/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuffer ret = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }
}
