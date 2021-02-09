package me.luliru.parctice.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指Offer38字符串的排列
 * Created by luliru on 2020/10/26.
 */
public class 剑指Offer38字符串的排列 {

    public static void main(String[] args) {
        new 剑指Offer38字符串的排列().permutation("aab");
    }

    private List<String> res = new ArrayList<>();

    public String[] permutation(String s) {
        int len = s.length();

        // 将字符串转化为数组并排序
        char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            arr[i] = s.charAt(i);
        }
        Arrays.sort(arr);

        LinkedList<Character> path = new LinkedList<>();
        boolean[] used = new boolean[len];
        backtrack(len,arr,path,used);

        return res.toArray(new String[]{});
    }

    public void backtrack(int len, char[] chooses, LinkedList<Character> path,boolean[] used) {
        if (path.size() == len) {
            // 加入结果
            StringBuilder builder = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                builder.append(path.get(i));
            }
            res.add(builder.toString());
            return;
        }

        for (int i = 0; i < len; i++) {
            char c = chooses[i];
            // 剪枝，过滤掉重复组合
            if (used[i] == true) {  //已选择过
                continue;
            }
            if (i > 0 && chooses[i] == chooses[i-1] && used[i-1] == true) {   //重复选择
                continue;
            }

            // 作选择
            path.addLast(c);
            used[i] = true;

            // backtrack();
            backtrack(len,chooses,path,used);

            // 撤销选择
            path.removeLast();
            used[i] = false;
        }
    }
}
