package com.company.arythmetic;

public class KthGrammarBetter {

    public static void main(String[] args) {
        System.out.println(new KthGrammarBetter().kthGrammar(4, 5));
    }

    /**
     * Not like in my solution, where I create a "word" from the level 3
     * This solution operates 0 and 1 throughout all the tree.
     *
     * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
     *
     * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
     *
     * Examples:
     * Input: N = 1, K = 1
     * Output: 0
     *
     * Input: N = 2, K = 1
     * Output: 0
     *
     * Input: N = 2, K = 2
     * Output: 1
     *
     * Input: N = 4, K = 5
     * Output: 1
     *
     * Explanation:
     * row 1: 0
     * row 2: 01
     * row 3: 0110
     * row 4: 01101001
     *
     *
     *                     0
     *               0           1
     *           0      1     1      0
     *         0  1   1  0   1  0   0  1
     *
     *
     */
    public int kthGrammar(int N, int K) {
        int p = 0;
        int size = (int) Math.pow(2, N-1); //the number of rooms on the floor N

        for(int i = 0; i < N-1; i++){     //wherever you are you may make a 1/2 of the tree and less 1 level to resolve this problem
                                          //note! In each for loop, this tree yet reduced!
                                          //we are not moving from the initial point
                                          //we just imagine this point sits in the lesser and lesser tree

            if(K <= size/2) {            //if you started from the left of the tree, your "sign" remains the same (0 if was 0, 1 if was 1)
                p = p == 0 ? 0 : 1;
            }
            else {                       //if you started from the right of the tree, your "sign" changes (1 if was 0, 0 if was 1)
                p = p == 0 ? 1 : 0;
                K = K-(size/2);
            }
            size = size/2;
        }
        return p;

    }

}
