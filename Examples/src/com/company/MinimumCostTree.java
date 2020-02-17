package com.company;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MinimumCostTree {

    /**
     *
     * Given an array arr of positive integers, consider all binary trees such that:
     *
     * Each node has either 0 or 2 children;
     * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
     * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
     * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [6,2,4]
     * Output: 32
     * Explanation:
     * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
     *
     *     24            24
     *    /  \          /  \
     *   12   4        6    8
     *  /  \               / \
     *
     *
     */


    public static void main(String[] args) {
        MinimumCostTree tree = new MinimumCostTree();
        int var = 1000;
        int array[] = IntStream.range(0, var).toArray();
        int result = tree.mctFromLeafValues(array);
        System.out.println("result=" + result);


    }

    private static AtomicInteger r = new AtomicInteger();

    public int mctFromLeafValues(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[][] memo = new int[arr.length][arr.length];

        return minSumOfNonLeafNodes(arr, 0, arr.length - 1, memo);

    }

    private int minSumOfNonLeafNodes(int[] arr, int startIndex, int endIndex, int[][] memo) {

        //$$$ - note, here recursion stops, start==end
        if (startIndex >= endIndex) {
            return 0;
        }

        if (memo[startIndex][endIndex]  !=  0) {
            return memo[startIndex][endIndex];
        }


        int  res = Integer.MAX_VALUE;

        for (int i = startIndex; i < endIndex; i++) {
            //first time it returns 0, when recursion stops at $$$
            int left = minSumOfNonLeafNodes(arr, startIndex, i, memo);
            //first time it returns 0, when recursion stops at $$$
            int right = minSumOfNonLeafNodes(arr, i + 1, endIndex, memo);

            int maxLeft = 0;

            int maxRight = 0;

            for (int j = startIndex; j <= i; j++) {
                maxLeft = Math.max(maxLeft, arr[j]);
            }

            for (int j = i + 1; j <= endIndex; j++) {
                maxRight = Math.max(maxRight, arr[j]);
            }

            int valueOfTheNonLeafNode = maxLeft * maxRight;

            //first time left==0 and right==0, we have only valueOfTheNonLeafNode present -
            //it means we are in the lowest NOT LEAF NODE
            res = Math.min(res, valueOfTheNonLeafNode + left + right);
        }
        memo[startIndex][endIndex] = res;
        System.out.println(r.getAndIncrement()+" memo=["+startIndex+"]["+endIndex+"]="+res);

        return res;
    }

}
