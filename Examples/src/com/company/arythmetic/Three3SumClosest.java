package com.company.arythmetic;

import java.util.Arrays;

public class Three3SumClosest {

    /**
     * Given an array nums of n integers and an integer target,
     * find three integers in nums such that the sum is closest to target. Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     */
    public int threeSumClosest(int[] nums, int target) {

        //sort , because in summary questions, you can "see the future" and eliminate certain loops, if you go in ascending order

        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;
        int sumResult = 0;

        for (int a = 0; a < nums.length; a++) {

            if (a > 0 && nums[a - 1] == nums[a]) {
                continue;
            }

            int b = a + 1;
            int c = nums.length - 1;

            while (b < c) {
                int sum = nums[a] + nums[b] + nums[c];
                int newDiff = Math.abs(target - sum);
                if (newDiff < minDiff) {
                    minDiff = newDiff;
                    sumResult = sum;
                }
                if (sum < target) {
                    b++;
                } else if (sum > target) {
                    c--;
                } else {
                    return sum;
                }
            }
        }
        return sumResult;
    }

}
