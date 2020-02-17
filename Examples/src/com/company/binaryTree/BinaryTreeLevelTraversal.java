package com.company.binaryTree;

import java.util.*;

public class BinaryTreeLevelTraversal {


    /**
     *
     * Output elements level by level starting from root
     *
     * Using recursive DFS here
     *
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

        List<List<Integer>> result = (new BinaryTreeLevelTraversal()).levelOrder(root);

        result.stream().forEach(e -> System.out.println(e));
    }


    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        Map<Integer, List<Integer>> leveledMap = new HashMap<>();

        leveling(0, root, leveledMap);

        int i  = 0;

        while (true) {

            List<Integer> subRes = leveledMap.get(i++);

            if(subRes==null){
                break;
            }

            result.add(subRes);

        }


        return result;

    }

    private void leveling(final int level, final TreeNode root, final Map<Integer, List<Integer>> leveledMap) {

        if (root != null) {

            List<Integer> levelNodes = leveledMap.get(level);

            if (levelNodes == null) {

                levelNodes = new ArrayList<>();
                leveledMap.put(level, levelNodes);

            }

            levelNodes.add(root.val);

            int nextLevel = level + 1;

            if (root.left != null) {
                leveling(nextLevel, root.left, leveledMap);
            }
            if (root.right != null) {
                leveling(nextLevel, root.right, leveledMap);
            }


        }

    }

}
