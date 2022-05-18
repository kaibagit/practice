package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**

 101. 对称二叉树

 给定一个二叉树，检查它是否是镜像对称的。



 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

 1
 / \
 2   2
 / \ / \
 3  4 4  3



 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

 1
 / \
 2   2
 \   \
 3    3


 */
public class LC101_对称二叉树 {

    public static void main(String[] args) {

    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            TreeNode m = queue.poll();

            if (n == null && m == null) {
                continue;
            }

            if (n == null || m == null || n.val != m.val) {
                return false;
            }

            queue.offer(n.left);
            queue.offer(m.right);

            queue.offer(n.right);
            queue.offer(m.left);
        }

        return true;
    }





































    public boolean isSymmetric_old(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isMirror(root.left,root.right);
    }

    public boolean isMirror(TreeNode left,TreeNode right) {
        if(left == null && right == null) {
            return true;
        }
        if(left == null || right == null) {
            return false;
        }
        return left.val == right.val && isMirror(left.left,right.right) && isMirror(left.right,right.left);
    }
}
