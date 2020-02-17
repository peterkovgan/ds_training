package com.company.binaryTree;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {


    /**
     *
     * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
     *
     * If two nodes are in the same row and column, the order should be from left to right.
     *
     *
     * Input: [3,9,20,null,null,15,7]
     *
     *    3
     *   /\
     *  /  \
     *  9  20
     *     /\
     *    /  \
     *   15   7
     *
     * Output:
     *
     * [
     *   [9],
     *   [3,15],
     *   [20],
     *   [7]
     * ]
     *
     * USE BFS / queue here, storing nodes with the level in the queue
     *
     *
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
        List<List<Integer>> result = (new BinaryTreeVerticalOrderTraversal()).verticalOrder(root);
        result.stream().forEach(e -> System.out.println(e));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        Map<Integer, List<Integer>> leveledMap = new HashMap<>();

        levelingVertical(0, root, leveledMap);

        Optional<Integer> opt = leveledMap.keySet().stream().min((e1, e2)->e1-e2);

        if(opt.isPresent()){
            int i  = opt.get();

            while (true) {

                List<Integer> subRes = leveledMap.get(i++);

                if(subRes==null){
                    break;
                }

                result.add(subRes);

            }

        }
        return result;
    }

    class LeveledNode{
        TreeNode treeNode;
        int level;
        public LeveledNode(TreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }


    private void levelingVertical(final int level, final TreeNode root, final Map<Integer, List<Integer>> leveledMap) {


        Queue<LeveledNode> queue = new LinkedList<>();

        if (root != null) {

            queue.add(new LeveledNode(root, level));

            while(!queue.isEmpty()) {


                LeveledNode node = queue.poll();

                int nodeLevel = node.level;

                List<Integer> levelNodes = leveledMap.get(nodeLevel);

                if (levelNodes == null) {
                    levelNodes = new ArrayList<>();
                    leveledMap.put(nodeLevel, levelNodes);
                }

                levelNodes.add(node.treeNode.val);
                if (node.treeNode.left != null) {
                    queue.add(new LeveledNode(node.treeNode.left, node.level-1));
                }
                if (node.treeNode.right != null) {
                    queue.add(new LeveledNode(node.treeNode.right, node.level+1));
                }

            }


        }

    }

}
