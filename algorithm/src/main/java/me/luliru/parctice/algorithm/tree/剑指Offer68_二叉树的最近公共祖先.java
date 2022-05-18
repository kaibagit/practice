package me.luliru.parctice.algorithm.tree;

import java.util.*;

/**
 * 二叉树的最近公共祖先
 * Created by luliru on 2021/2/12.
 */
public class 剑指Offer68_二叉树的最近公共祖先 {


    public static void main(String[] args) {
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n3.left = n5;
        n3.right = n1;

        new 剑指Offer68_二叉树的最近公共祖先().lowestCommonAncestor_backtracking(n3, n5, n1);
    }

    /**
     * 递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    private TreeNode dfs(TreeNode head, TreeNode p, TreeNode q) {
        if (head == null) {
            return null;
        }

        if (head.val == p.val || head.val == q.val) {
            return head;
        }

        TreeNode leftResult = dfs(head.left, p, q);
        TreeNode rightResult = dfs(head.right, p, q);

        if (leftResult == null) {
            return rightResult;
        }

        if (rightResult == null) {
            return leftResult;
        }

        return head;
    }


    /**
     * 存储父节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_storeParent(TreeNode root, TreeNode p, TreeNode q) {
        // 1、构建头节点map
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, parentMap);

        // 2、记录p的路径
        Set<TreeNode> pathSet = new HashSet<>();
        TreeNode node = p;
        while (node != null) {
            pathSet.add(node);
            node = parentMap.get(node);
        }

        // 3、遍历q的路径，查找与p重叠的部分
        node = q;
        while (node != null) {
            if (pathSet.contains(node)) {
                return node;
            }
            node = parentMap.get(node);
        }

        return null;
    }

    private void buildParentMap(TreeNode head, Map<TreeNode, TreeNode> parentMap) {
        TreeNode left = head.left;
        if (left != null) {
            parentMap.put(left, head);
            buildParentMap(left, parentMap);
        }

        TreeNode right = head.right;
        if (right != null) {
            parentMap.put(right, head);
            buildParentMap(right, parentMap);
        }
    }




    /**
     * 回溯法，将2个节点的路径找出来，然后查找最后一个相同的节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_backtracking(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pPath = new LinkedList<>();
        LinkedList<TreeNode> qPath = new LinkedList<>();

        find(root, p, pPath);
        find(root, q, qPath);

        TreeNode ans = null;
        int i = 0;
        while (i < pPath.size() && i < qPath.size()) {  // 退出条件：i = pPath.size() || i = qPath.size()
            if (pPath.get(i).val != qPath.get(i).val) {
                break;
            }

            ans = pPath.get(i); // 随便选一个，pPath或者qPath都行
            i++;
        }

        return ans;
    }

    private boolean find(TreeNode head, TreeNode target, LinkedList<TreeNode> path) {
        if (head == null) {
            return false;
        }

        path.addLast(head);

        if (head.val == target.val) {
            return true;
        }

        // 进入左子树
        if (find(head.left, target, path)) {
            return true;
        }

        // 进入右子树
        if (find(head.right, target, path)) {
            return true;
        }

        // 撤销
        path.removeLast();
        return false;
    }















































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
    public TreeNode lowestCommonAncestor_old(TreeNode root, TreeNode p, TreeNode q) {
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
