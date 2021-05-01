package me.luliru.parctice.algorithm.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 二叉树的最近公共祖先
 * Created by luliru on 2021/2/12.
 */
public class 剑指Offer68_二叉树的最近公共祖先 {


    public TreeNode lowestCommonAncestor_210301(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p , q);
    }

    private TreeNode find(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        if (node == p || node == q) {
            return node;
        }

        TreeNode leftResult = find(node.left, p , q);
        TreeNode rightResult = find(node.right, p , q);
        if (leftResult != null && rightResult != null) {
            return node;
        }

        return leftResult == null ? rightResult : leftResult;
    }




































    /**
     * 存储父节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1、build每个节点指向父节点的Map
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(parentMap, root);

        // 2、将p到根节点的链路记录下来
        Set<TreeNode> visited = new HashSet<>();
        TreeNode point = p;
        while (point != null) {
            visited.add(point);
            point = parentMap.get(point);
        }

        // 3、查找p到根节点的链路，找到第一个跟p链路重叠的元素
        point = q;
        while (point != null) {
            if (visited.contains(point)) {
                return point;
            }
            point = parentMap.get(point);
        }

        return null;
    }

    private void buildParentMap(Map<TreeNode, TreeNode> parentMap, TreeNode node) {
        if (node.left != null) {
            parentMap.put(node.left, node);
            buildParentMap(parentMap, node.left);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            buildParentMap(parentMap, node.right);
        }
    }

    /**
     * 递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_v2(TreeNode root, TreeNode p, TreeNode q) {
        return findPorQ(root, p, q);
    }

    /**
     * 在树中查找p或者q
     * @param node
     * @param p
     * @param q
     * @return p或者q节点，如果没找到，返回null
     */
    public TreeNode findPorQ(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        if (node == p || node == q) {   //找到了p或q，则将其返回，代表已找到
            return node;
        }
        TreeNode leftResult = findPorQ(node.left, p, q);
        TreeNode rightResult = findPorQ(node.right, p, q);
        if (leftResult == null) {   // 左子树不存在p和q，则必然在右子树上
            return rightResult;
        }
        if (rightResult == null) {  // 右子树不存在p和q，则必然在左子树上
            return leftResult;
        }
        return node;    // 左右子树各有一个p、q，则说明该节点即为公共祖先
    }
}
