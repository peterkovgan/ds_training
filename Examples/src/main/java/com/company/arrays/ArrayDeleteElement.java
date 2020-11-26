package com.company.arrays;

public class ArrayDeleteElement {


    /**
     * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     *
     * https://leetcode.com/explore/learn/card/fun-with-arrays/526/deleting-items-from-an-array/3247/
     *
     * Test cases:
     *
     * [0,1,2,2,3,0,4,2]
     * 2
     *
     * [3,3]
     * 3
     *
     * [4,5]
     * 4
     *
     */

    public int removeElement(int[] nums, int val) {

        int currentMaxLength = nums.length;

        for(int i=0; i < currentMaxLength; i++){

            while(nums[i] == val && currentMaxLength > i){

                for(int y=i; y < currentMaxLength - 1; y++){
                    nums[y] = nums[y + 1];

                }

                currentMaxLength--;

            }

        }

        return currentMaxLength;
    }

}
