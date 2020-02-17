package com.company.palindromes;

public class LongestPalindrome {

    //https://leetcode.com/problems/longest-palindromic-substring/solution/

    public static void main(String[] args) {
        //System.out.println(new LongestPalindrome().longestPalindromeFromCenter("babad"));
        System.out.println(new LongestPalindrome().longestPalindromeDynamic("ttttttttdtbababdklluy"));
        //System.out.println(new LongestPalindrome().longestPalindromeDynamic("cbbd"));
    }

    int [][] memo;
    public String longestPalindromeDynamic(String s) {
        if(s==null) return "";
        int length = s.length();
        if(length==0) return "";
        memo = new int[length][length];

        String longest = "";

        for(int i = 0; i < length; i++){
            memo[i][i] = 1;                          //fill by 1 char
            if(longest.length()<2) {
                longest = s.charAt(i) + "";          //register longest as 1 char
            }

            if(i + 1 < length){                    //working with 2 chars
                if(s.charAt(i)==s.charAt(i+1)){    //on equality condition:
                    memo[i][i+1] = 1;              //fill by 2 chars
                    if(i + 1 < length-1) {         //register longest as 2 chars
                        longest = s.substring(i, i + 2);  //situation 1 is exactly like situation 2, just other way of taking substring
                    }else{
                        longest = s.substring(i);         //situation 2
                    }
                }
            }

        }

        //System.out.println(longest);

        int maxLength = longest.length();          //register max length

        for(int y = 1; y < length - 1; y++){       //Now go through entire array, stopping on each station (Y) from 1 to N-1
            int max = length/2;
            int width = 1;                         //Explore Y to left and to right on WIDTH=1,2,3,4,5....
            while(width <= max){                   //Ensure that  WIDTH is never greater than MAX = length/2
                if(y - width >= 0 && y + width < length){  //if in borders
                    if(memo[y - width + 1][y + width -1] == 1 && s.charAt(y - width)==s.charAt(y + width)){   //if internal memo filled by 1 and on this WIDTH we are equal
                        if(y+width-(y-width) + 1 >= maxLength) {  //if selected palindrome is wider than prev max
                            maxLength = y+width-(y-width) + 1;    //edit max
                            if (y + width < length - 1) {         //edit max palindrome
                                longest = s.substring(y - width, y + width + 1);
                            } else {
                                longest = s.substring(y - width);
                            }

                        }
                        memo[y - width][y + width] = 1;
                    }
                }else{
                    break;
                }
                width++;
            }
        }

        //if the case above was appropriate for aBa types
        //this case below is appropriate for aabb types:
        //it is like the case above, but starts from the width 2
        //and the right branch is not "on equal distance from the center"
        //but on reduced by 1 distance from the Y

        for(int y = 1; y < length - 1; y++){
            int max = length/2;
            int width = 2;
            while(width <= max){
                if(y - width + 1>= 0 && y + width < length){
                    if(memo[y - width + 1 + 1][y + width -1] == 1 && s.charAt(y - width + 1)==s.charAt(y + width)){
                        if(y+width-(y-width +1) + 1 >= maxLength) {
                            maxLength = y+width-(y-width + 1) + 1;
                            if (y + width < length - 1) {
                                longest = s.substring(y - width + 1, y + width + 1);
                            } else {
                                longest = s.substring(y - width + 1);
                            }

                        }
                        memo[y - width + 1][y + width] = 1;
                    }
                }else{
                    break;
                }
                width++;
            }
        }


        return longest;
    }


    /**
     * Approach 4: Expand Around Center
     * In fact, we could solve it in O(n^2)
     * time using only constant space.
     *
     * We observe that a palindrome mirrors around its center. Therefore,
     * a palindrome can be expanded from its center, and there are only 2n −1 such centers.
     *
     * You might be asking why there are 2n - 12n−1 but not nn centers? The reason
     * is the center of a palindrome can be in between two letters.
     * Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.
     *
     */
    public String longestPalindromeFromCenter(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);  //bAb case
            int len2 = expandAroundCenter(s, i, i + 1);  //aabb case
            int len = Math.max(len1, len2);
            if (len > end - start) {                     //if len increased
                start = i - (len - 1) / 2;               //new start
                end = i + len / 2;                       //new end
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
