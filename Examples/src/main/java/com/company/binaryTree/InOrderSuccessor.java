package com.company.binaryTree;

import java.util.ArrayDeque;

public class InOrderSuccessor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode l1 = new TreeNode(3);
        TreeNode r1 = new TreeNode(8);
        root.left = l1;
        root.right = r1;
        TreeNode l21 = new TreeNode(1);
        TreeNode r21 = new TreeNode(4);
        l1.left = l21;
        l1.right = r21;
        TreeNode l22 = new TreeNode(5);
        TreeNode r22 = new TreeNode(9);
        r1.left = l22;
        r1.right = r22;
        TreeNode result = (new InOrderSuccessor()).inorderSuccessor(root, r1);
        System.out.println("result: "+result.val);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        // the successor is somewhere lower in the right subtree
        // successor: one step right and then left till you can
        if (p.right != null) {
            p = p.right;
            while (p.left != null) p = p.left;
            return p;
        }

        // the successor is somewhere upper in the tree
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        int inorder = Integer.MIN_VALUE;

        // inorder traversal : left -> node -> right
        while (!stack.isEmpty() || root != null) {
            // 1. go left till you can
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 2. all logic around the node
            root = stack.pop();
            // if the previous node was equal to p
            // then the current node is its successor
            if (inorder == p.val) return root;
            inorder = root.val;

            // 3. go one step right
            root = root.right;
        }

        // there is no successor
        return null;
    }





}
