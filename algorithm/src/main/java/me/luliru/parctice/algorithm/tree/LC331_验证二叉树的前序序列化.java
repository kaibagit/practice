package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * LC331_验证二叉树的前序序列化
 * Created by luliru on 2021/3/12.
 */
public class LC331_验证二叉树的前序序列化 {

    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(new LC331_验证二叉树的前序序列化().isValidSerialization_210312_v3(preorder));
    }

    /**
     * dfs
     * @param preorder
     * @return
     */
    public boolean isValidSerialization_210312_v1(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return true;
        }
        String[] valStrArr = preorder.split(",");
        Object[] result = dfs(valStrArr, 0, valStrArr.length);
        if (Boolean.FALSE.equals(result[0])) {
            return false;
        }
        if ((int)result[1] != valStrArr.length) {
            return false;
        }
        return true;
    }

    private Object[] dfs(String[] valStrArr, int start, int end) {  // 搜索范围：[start, end)
        if (start >= valStrArr.length) {
            return new Object[]{false, 0};
        }
        if (start == end) {
            return new Object[]{false, 0};
        }

        if (valStrArr[start].equals("#")) {
            return new Object[]{true, 1};
        } else {
            Object[] leftResult = dfs(valStrArr, start + 1, end);
            if (Boolean.FALSE.equals(leftResult[0])) {
                return new Object[]{false, 0};
            }
            Object[] rightResult = dfs(valStrArr, start + (int)leftResult[1] + 1, end);
            if (Boolean.FALSE.equals(rightResult[0])) {
                return new Object[]{false, 0};
            }
            return new Object[]{true, 1 + (int)leftResult[1] + (int)rightResult[1]};
        }
    }

    /**
     * 栈+消除法
     * @param preorder
     * @return
     */
    public boolean isValidSerialization_210312_v2(String preorder) {
        String[] valStrArr = preorder.split(",");
        LinkedList<String> stack = new LinkedList<>();
        for (int i = 0; i < valStrArr.length; i++) {
            String curr = valStrArr[i];

            if (!curr.equals("#")) {
                stack.push(curr);
                continue;
            }

            // 如果栈中为v,#，再碰到#的情况，需要将其消除为#；消除之后，还要继续看下一轮是否满足v,#
            while (!stack.isEmpty() && stack.peek().equals("#")) {
                stack.pop();    // 弹出#
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();    // 弹出父节点
            }

            // 无法消除，push#
            stack.push(curr);
        }

        // preorder=''
        if (stack.isEmpty()) {
            return true;
        }
        // preorder='#'，或最终消除以后，只剩下一个#
        if (stack.size() == 1 && stack.peek().equals("#")) {
            return true;
        }

        return false;
    }

    /**
     * 栈+槽位法
     * @param preorder
     * @return
     */
    public boolean isValidSerialization_210312_v3(String preorder) {
        String[] valStrArr = preorder.split(",");
        if (valStrArr.length == 0) {
            return true;
        }
        if (valStrArr[0].equals("#")) {
            if (valStrArr.length > 1) {     // 考虑："#,#,#"
                return false;
            }
            return true;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(2);  // 前面已经排除了preorder = '#'的情况
        for (int i = 1; i < valStrArr.length; i++) {
            String curr = valStrArr[i];

            // 如果遇到了空节点，则要消耗一个槽位；
            // 如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
            if (stack.isEmpty()) {      // 考虑："1,#,#,1"
                return false;
            }
            int slot = stack.pop();
            --slot;
            if (slot != 0) {
                stack.push(slot);
            }

            if (!curr.equals("#")) {
                stack.push(2);
            }
        }

        return stack.isEmpty();
    }

    /**
     * 槽位法 + 计数
     * @param preorder
     * @return
     */
    public boolean isValidSerialization_210312_v4(String preorder) {
        String[] valStrArr = preorder.split(",");
        if (valStrArr.length == 0) {
            return true;
        }
        if (valStrArr[0].equals("#")) {
            if (valStrArr.length > 1) {     // 考虑："#,#,#"
                return false;
            }
            return true;
        }

        int slot = 2;  // 处理根节点
        for (int i = 1; i < valStrArr.length; i++) {
            String curr = valStrArr[i];

            // 如果遇到了空节点，则要消耗一个槽位；
            // 如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
            if (slot == 0) {        // 考虑："1,#,#,1"
                return false;
            }
            --slot;

            if (!curr.equals("#")) {
                slot += 2;
            }
        }

        return slot == 0;
    }

    /**
     * 出度法
     * @param preorder
     * @return
     */
    public boolean isValidSerialization_210312_v5(String preorder) {
        String[] valStrArr = preorder.split(",");
        if (valStrArr.length == 0) {
            return true;
        }
        if (valStrArr[0].equals("#")) {
            if (valStrArr.length > 1) {     // 考虑："#,#,#"
                return false;
            }
            return true;
        }

        int out = 2;  // 处理根节点
        for (int i = 1; i < valStrArr.length; i++) {
            String curr = valStrArr[i];

            // 如果遇到了空节点，则要增加一个入度，即消耗一个出度；
            // 如果遇到了非空节点，则除了消耗一个出度外，还要再补充两个出度。
            if (out == 0) {        // 考虑："1,#,#,1"
                return false;
            }
            --out;

            if (!curr.equals("#")) {
                out += 2;
            }
        }

        return out == 0;
    }
}
