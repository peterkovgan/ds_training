package com.company;

public class SearcheableInUnsortedArray {

    /**
     * https://leetcode.com/discuss/interview-question/352743/Google-or-Onsite-or-Guaranteed-Binary-Search-Numbers
     *
     *  Find elements searchable in unsorted array by binary search
     *  1, 3, 2
     *
     *  crete Max till now array:
     *
     *  MIN_VALUE,  1, 3
     *
     *  crete Min till now array: (go from right to left)
     *
     *  2, 2, MAX_VALUE
     *
     *  iterate through input selecting elements >  "Max till now" and < "Min till now array:"
     *
     *  only 1 gets good with that:
     *
     *  Result 1
     *
     */

    public static void main(String[] args) {
        SearcheableInUnsortedArray solution = new SearcheableInUnsortedArray();
        System.out.println(solution.howMany(new int[] {1, 3, 2}));
        System.out.println(solution.howMany(new int[] {2, 1, 3, 5, 4, 6}));
        System.out.println(solution.howMany(new int[] {1, 5, 7, 11, 12, 18}));
        System.out.println(solution.howMany(new int[] {5, 4, 3, 2, 1, 0}));
        System.out.println(solution.howMany(new int[] {1, 3, 2, 4, 5, 7, 6, 8}));
    }

    public int howMany(int[] input) {
        int n = input.length;
        if (n == 0) {
            return 0;
        }

        int[] maxLeft = new int[n];
        int[] minRight = new int[n];

        int maxTillNow = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxLeft[i] = maxTillNow;
            maxTillNow = Math.max(input[i], maxTillNow);
        }

        int minTillNow = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            minRight[i] = minTillNow;
            minTillNow = Math.min(input[i], minTillNow);
        }

        int count = 0;
        for (int i =0 ; i < n; i++) {
            if (input[i] > maxLeft[i] && input[i] < minRight[i]) {
                count++;
            }
        }

        return count;
    }


}
