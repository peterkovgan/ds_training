package com.company;

import java.util.Arrays;

public class NextGreatestPermutation {


    public static void main(String[] args) {
        //2,1,3 - expected result
        int[] dist = new int[]{1, 3, 2};
        new NextGreatestPermutation().nextPermutation(dist);
        for (Integer list : dist) {
            System.out.println(list);
        }
    }

    public void nextPermutation(int[] nums) {   //3,1,2
        int swapIndex = -1;
        int lastWorkingIndex = -1;
        for (int widerIndex = nums.length - 1; widerIndex > 0; widerIndex--) {
            for (int begunok = widerIndex-1; begunok >= 0; begunok--) {
                if (nums[widerIndex] > nums[begunok]) {
                    if( begunok > swapIndex){
                        swapIndex = begunok;
                        lastWorkingIndex = widerIndex;
                        break;
                    }
                }
            }
        }
        if(swapIndex > -1){
            swap(lastWorkingIndex, swapIndex, nums);
            reorder(swapIndex+1, nums);
            return;
        }
        Arrays.sort(nums);
    }

    private void reorder(int i, int[] nums) {
        if( i > nums.length - 1) return;
        Arrays.sort(nums, i, nums.length);
    }

    private void swap(int y, int i, int[] nums) {
        int temp = nums[y];
        nums[y] = nums[i];
        nums[i] = temp;
    }

}
