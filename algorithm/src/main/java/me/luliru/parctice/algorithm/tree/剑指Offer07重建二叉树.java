package me.luliru.parctice.algorithm.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指Offer07重建二叉树
 * Created by luliru on 2020/10/20.
 */
public class 剑指Offer07重建二叉树 {

    private Map<Integer/* value */,Integer/* index */> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
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
