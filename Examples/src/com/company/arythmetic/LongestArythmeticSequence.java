package com.company.arythmetic;

import java.util.HashMap;
import java.util.Map;

public class LongestArythmeticSequence {

    //https://leetcode.com/problems/longest-arithmetic-sequence/

    /**
     * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
     *
     * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k]
     * with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i]
     * are all the same value (for 0 <= i < B.length - 1).
     *
     * Solution:
     *
     * Each element of the array contains map:
     * key - arithmetic difference between this element and some preceding element
     * value - number elements with such differences that occurred so far (starts from 2)
     *
     * In the array like that: 1,3,5
     * difference is 2, and here you see 2 positions:
     * 1-st when i = 1 and j = 0
     * 2-d  when i = 2 and j = 1
     *
     * 1,     3,       5
     *       (2,2)                 max=2
     *       (2,2)     (2,3)       max=3
     *
     */

    public static void main(String[] args) {

    }
    public int longestArithSeqLength(int[] A) {
        Map<Integer,Integer>[] dp = new HashMap[A.length];
        int max = 0;
        for(int i=0; i<A.length; i++){                           //Full iteration
            if(dp[i] == null) dp[i] = new HashMap();             //assign a hash map
            for(int j=0; j<i; j++){                              //iteration till the max in the first iteration (exclusive)
                int diff = A[i] - A[j];                          //find the diff
                if(dp[j].get(diff) == null){                     //if in lefter diff not found, init lefter to 2
                    dp[i].put(diff,2);
                }else{
                    dp[i].put(diff, dp[j].get(diff)+1);          //if in lefter diff is found, set righter diff to lefter diff + 1
                }
                max = Math.max(max, dp[i].get(diff));            //recalculate the whole max
            }
        }
        return max;
    }

    public int longestArithSeqLengthA(int[] A) {
        int res = 0;
        //array to keep differences and sequence length. dp[i][j] meaning is
        //sequence of length i and difference j. Because of the problem restrictions we can
        //allocate array for differences beforehand (0 <= A[i] <= 10000)
        int[][] dp = new int[A.length][20001];

        //set the right border of the sequence
        for (int i = 0; i < A.length; i++) {
            //start checking sequences by moving left border, so sequences from 0..i to i-1..i will
            //be checked
            for (int j = 0; j < i; j++) {
                //get the difference i and j elements, apply 10.000 shift so we can use array indexes
                int dif = (A[i] - A[j]) + 10000;
                //check if we have seen this difference before, if not - make it of length 2 (i and j makes at least
                //2 elements sequence)
                dp[i][dif] = (dp[j][dif] == 0 ? 1 : dp[j][dif]) + 1;
                //keep running max so we dont have to scan our dp matrix at the end
                res = Math.max(res, dp[i][dif]);
            }
        }
        return res;
    }

}
