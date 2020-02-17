package com.company.binaryTree;

import java.util.ArrayDeque;

/**
 * DFT in recursion , val[space]val[space] and because of the post-order the root will be printed last
 * Pay attention:
 * On deserialization we put all values in dequeue(queue)
 * And then take from last in the queue (from the root)
 *
 * And start manipulate by the lower/upper border, trying to submit the rest of the queue to both (left and right parts)
 * the element will be only accepted on the left or on the right, or on both, but every time it will be either accepted or not
 *
 * Pay attention: serialization went : left, right, me
 * Deserialization is :                me, right, left (the opposite order)
 *
 */


public class SerializeDeserializeBTBetter {

    StringBuilder postorder(TreeNode root, StringBuilder sb){
        if(root == null) return sb;
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.val);
        sb.append(' ');
        return sb;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "";
        StringBuilder sb = postorder(root, new StringBuilder());
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    TreeNode helper(int low, int up, ArrayDeque<Integer> nums){
        if(nums.isEmpty()) return null;
        int val = nums.getLast();
        if(val < low || val > up){
            return null;
        }
        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper(val, up, nums);
        root.left  = helper(low, val, nums);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        for (String s : data.split("\\s+"))
            nums.add(Integer.valueOf(s));
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }

}
