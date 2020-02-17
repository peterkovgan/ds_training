package com.company.dynamic;

public class DecodeWays {

    /**
     * https://leetcode.com/problems/decode-ways/
     *
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     *
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     *
     */

    public int numDecodings(String s) {

        if (s.startsWith("0") || s.length() == 0)    return 0;

        int[] dp = new int[s.length() + 1];

        dp[1] = 1;

        for (int i = 2; i <= s.length(); i++) {

            if ( s.charAt(i - 1) != '0' ) {   //ignore '0'-s they provide no mappings as a single, they can only play role in pairs , like 10, 20
                dp[i] += dp[i - 1];           //memoization of next (i) is what was inside (from start it is 0) plus what was 1 step ago (from start 1),
                //thus it will be 1,1,2,3,4,5
                //on indices    0 1 2 3 4 5 6, so on String of length 6, the result will be 5 "ways"
                //you always add to the summary of this index, considering the one was in the previous index
            }

            int twoDigsValue = Integer.valueOf(s.substring(i - 2, i));   //look on 2 digs

            if (twoDigsValue <= 26 &&  twoDigsValue >= 10) {
                dp[i] += i == 2 ? 1 : dp[i - 2];
                //first (on index 2 add 1), later add what was in DP index -2
                //you sort of ignore the number achieved in i-1 and consider one that achieved in i-2, adding it to the summary of this index
            }

        }

        return dp[s.length()];
    }

}
