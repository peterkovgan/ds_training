package com.company.arrays;

public class RemoveDupsInOrderedArray {


    /**
     * Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * https://leetcode.com/explore/learn/card/fun-with-arrays/526/deleting-items-from-an-array/3248/
     *
     */

    public int removeDuplicates(int[] nums) {

        int i=0;
        int tracker = 0;

        for(i=0; i<nums.length-1;){ //note that iterate here not to the last element and not advancing i here, but later

            int val =  nums[i];
            //find first that not the same
            int nextDiffIndex = fidNextDiffIndex(nums,val,i+1);

            if(nextDiffIndex==-1){//all elements after i are equal to nums[i]
                //that means, nothing to do from here
                break;
            }else{
                //swap the first difference found with the element in tracker+1
                nums[tracker+1]=nums[nextDiffIndex];
                //advance i to the place where the next greater value found
                i=nextDiffIndex;
                //move tracker to the next place that could be filled by the number in the future (if the greater number found)
                tracker++;
            }
        }
        return tracker+1;

    }

    private int fidNextDiffIndex(int[] nums, int val, int indexToStart){
        for(int y = indexToStart; y<nums.length; y++){
            if(val!=nums[y]){
                return y;
            }
        }
        return -1;
    }


}
