package com.company.binaryTree;

import java.util.*;

public class BinaryTreeVerticalOrderTraversalBetter {


    /**
     * The improvement: I calculate the MIN level on the flight
     *
     * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
     * <p>
     * If two nodes are in the same row and column, the order should be from left to right.
     * <p>
     * <p>
     * Input: [3,9,20,null,null,15,7]
     * <p>
     * 3
     * /\
     * /  \
     * 9  20
     * /\
     * /  \
     * 15   7
     * <p>
     * Output:
     * <p>
     * [
     * [9],
     * [3,15],
     * [20],
     * [7]
     * ]
     * <p>
     * USE BFS / queue here, storing nodes with the level in the queue
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode l1 = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        root.left = l1;
        root.right = r1;
        TreeNode l21 = new TreeNode(3);
        TreeNode r21 = new TreeNode(4);
        l1.left = l21;
        l1.right = r21;
        TreeNode l22 = new TreeNode(5);
        TreeNode r22 = new TreeNode(6);
        r1.left = l22;
        r1.right = r22;
        List<List<Integer>> result = (new BinaryTreeVerticalOrderTraversalBetter()).verticalOrder(root);
        result.stream().forEach(e -> System.out.println(e));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        Map<Integer, List<Integer>> leveledMap = new HashMap<>();



        int min = levelingVertical(0, root, leveledMap);

        int i = min;

        while (true) {

            List<Integer> subRes = leveledMap.get(i++);

            if (subRes == null) {
                break;
            }

            result.add(subRes);

        }


        return result;
    }


    private int levelingVertical(final int level, final TreeNode root, final Map<Integer, List<Integer>> leveledMap) {

        int minLevel = 0;

        Map<Integer, Integer> levels = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {

            queue.add(root);
            levels.put(root.val, level);


            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                int nodeLevel = levels.get(node.val);
                if(minLevel>nodeLevel){
                    minLevel = nodeLevel;
                }

                List<Integer> levelNodes = leveledMap.get(nodeLevel);

                if (levelNodes == null) {
                    levelNodes = new ArrayList<>();
                    leveledMap.put(nodeLevel, levelNodes);
                }

                levelNodes.add(node.val);

                if (node.left != null) {
                    levels.put(node.left.val, nodeLevel - 1);
                    queue.add(node.left);
                }

                if (node.right != null) {
                    levels.put(node.right.val, nodeLevel + 1);
                    queue.add(node.right);
                }

            }


        }

        return minLevel;

    }

}
