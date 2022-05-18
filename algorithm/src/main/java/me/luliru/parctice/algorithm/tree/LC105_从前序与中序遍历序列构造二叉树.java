package me.luliru.parctice.algorithm.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指Offer07重建二叉树
 * Created by luliru on 2020/10/20.
 */
public class LC105_从前序与中序遍历序列构造二叉树 {

    public static void main(String[] args) {
        new LC105_从前序与中序遍历序列构造二叉树().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1,0, inorder.length - 1, inorderIndexMap);
    }

    private TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int inorderStart, int inorderEnd, Map<Integer, Integer> inorderIndexMap) {
        // 处理边界
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        int headVal = preorder[preorderStart];
        int headIndex = inorderIndexMap.get(headVal);
        int leftTreeSize = headIndex - inorderStart;
        int rightTreeSize = inorderEnd - headIndex;
        TreeNode head = new TreeNode(headVal);
        head.left = buildTree(preorder,
                preorderStart + 1, preorderStart + 1 + leftTreeSize - 1,
                inorderStart, headIndex - 1,
                inorderIndexMap);
        head.right = buildTree(preorder,
                preorderStart + 1 + leftTreeSize - 1 + 1, preorderEnd,
                headIndex + 1, inorderEnd,
                inorderIndexMap);

        return head;
    }














































    public TreeNode buildTree_210309(int[] preorder, int[] inorder) {
        Map<Integer,Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return recurse(preorder, inorderIndexMap, 0, preorder.length - 1,0);
    }

    private TreeNode recurse(int[] preorder, Map<Integer,Integer> inorderIndexMap, int preStart, int preEnd, int inStart) {
        if (preStart > preEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        int rootIndex = inorderIndexMap.get(rootVal);
        int leftLength = rootIndex - inStart;
//        int rightLength = end - rootIndex;

        TreeNode root = new TreeNode(rootVal);
        root.left = recurse(preorder, inorderIndexMap, preStart + 1, preStart + leftLength, inStart);
        root.right = recurse(preorder, inorderIndexMap, preStart + leftLength + 1, preEnd, rootIndex + 1);
        return root;
    }










































    private Map<Integer/* value */,Integer/* index */> indexMap = new HashMap<>();

    public TreeNode buildTree_old(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0) {
            return null;
        }

        for(int i=0;i<inorder.length;i++) {
            indexMap.put(inorder[i],i);
        }
        return recurive(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }

    private TreeNode recurive(int[] preorder, int[] inorder,int preLeft,int preRight,int inLeft,int inRight) {
        int rootVal = preorder[preLeft];
        TreeNode rootNode = new TreeNode(rootVal);

        if(preLeft == preRight) {
            return rootNode;
        }

        int rootIndexOfInorder = indexMap.get(rootVal);
        int leftLength = rootIndexOfInorder - inLeft;
//        int rightLength = inRight - rootIndexOfInorder;


        rootNode.left = recurive(preorder,inorder,preLeft+1,preLeft+leftLength,inLeft,rootIndexOfInorder-1);
        rootNode.right = recurive(preorder,inorder,preLeft+leftLength+1,preRight,rootIndexOfInorder+1,inRight);
        return rootNode;
    }
}
