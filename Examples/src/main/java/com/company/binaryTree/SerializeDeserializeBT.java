package com.company.binaryTree;

import java.util.*;

public class SerializeDeserializeBT {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> arrayIndex = new HashMap<>();
        queue.add(root);
        arrayIndex.put(root, 1);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int index = arrayIndex.get(node);
            int val = node.val;
            sb.append(index);
            sb.append("-");
            sb.append(val);
            sb.append("|");
            if (node.left != null) {
                int nIndex = index * 2;
                queue.add(node.left);
                arrayIndex.put(node.left, nIndex);
            }
            if (node.right != null) {
                int nIndex = index * 2 + 1;
                queue.add(node.right);
                arrayIndex.put(node.right, nIndex);
            }
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;
        StringTokenizer strt = new StringTokenizer(data, "|");
        Map<Integer, Integer> indexMap = new HashMap<>();
        boolean first = false;
        int firstValue = 0;
        while (strt.hasMoreTokens()) {
            String str = strt.nextToken();
            int b = str.indexOf("-");
            int index = Integer.valueOf(str.substring(0, b));
            int value = Integer.valueOf(str.substring(b + 1));
            if (!first) {
                firstValue = value;
                first = true;
            }
            indexMap.put(index, value);
        }

        TreeNode root = new TreeNode(firstValue);
        req(root, 1, indexMap);
        return root;
    }

    private void req(TreeNode node, int nodeIndex, Map<Integer, Integer> indexMap) {
        int leftNode = nodeIndex * 2;
        int rightNode = nodeIndex * 2 + 1;
        if (indexMap.keySet().contains(leftNode)) {
            TreeNode nodeLeft = new TreeNode(indexMap.get(leftNode));
            node.left = nodeLeft;
            req(nodeLeft, leftNode, indexMap);
        }
        if (indexMap.keySet().contains(rightNode)) {
            TreeNode nodeRight = new TreeNode(indexMap.get(rightNode));
            node.right = nodeRight;
            req(nodeRight, rightNode, indexMap);
        }
    }
}
