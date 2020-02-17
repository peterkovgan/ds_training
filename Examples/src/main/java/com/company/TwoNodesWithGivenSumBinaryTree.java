package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * https://algorithmsandme.com/two-numbersnodes-with-given-sum-in-binary-search-tree-2sum-problem/
 *
 */

public class TwoNodesWithGivenSumBinaryTree {

    static class TreeNode {

        TreeNode left;
        TreeNode right;
        int value;


        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.setValue(15);

        TreeNode left1 = new TreeNode();
        left1.setValue(10);
        root.setLeft(left1);

        TreeNode right1 = new TreeNode();
        right1.setValue(20);
        root.setRight(right1);

        TreeNode left11 = new TreeNode();
        left11.setValue(8);
        left1.setLeft(left11);

        TreeNode right11 = new TreeNode();
        right11.setValue(12);
        left1.setRight(right11);

        TreeNode left21 = new TreeNode();
        left21.setValue(16);
        right1.setLeft(left21);

        TreeNode right21 = new TreeNode();
        right21.setValue(25);
        right1.setRight(right21);


        ArrayList<Pair<Integer, Integer>> result =   new TwoNodesWithGivenSumBinaryTree().findPairsWithGivenSum(root, 32);
        System.out.println(result);
    }

    public ArrayList<Pair<Integer, Integer>> findPairsWithGivenSum(TreeNode root, int sum) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();

        TreeNode cur1 = root;
        TreeNode cur2 = root;

        while (!s1.isEmpty() || !s2.isEmpty() ||  cur1 != null || cur2 != null) {
            if (cur1 != null || cur2 != null) {
                if (cur1 != null) {
                    s1.push(cur1);
                    cur1 = cur1.getLeft();
                }

                if (cur2 != null) {
                    s2.push(cur2);
                    cur2 = cur2.getRight();
                }
            } else {
                int val1 = (int) s1.peek().getValue();
                int val2 = (int) s2.peek().getValue();

                if (s1.peek() == s2.peek()) break;

                if (val1 + val2 == sum)
                    result.add(new Pair(val1, val2));

                if (val1 + val2 < sum) {
                    cur1 = s1.pop();
                    cur1 = cur1.getRight();
                } else {
                    cur2 = s2.pop();
                    cur2 = cur2.getLeft();
                }
            }
        }

        return result;
    }

}
