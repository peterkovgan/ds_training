package com.company.arythmetic;

import java.util.Arrays;

/**
 *
 *
 * In sorted array find a range of some target appearance (the target could be duplicated)
 *
 **/

public class FirstLastPositionSortedArray {

    public static void main(String[] args) {

        int[] array = new int[]{-2,-1,-1,-1,-1,0,0,0,3,4};
        System.out.println(Arrays.toString(new FirstLastPositionSortedArray().searchRange(array, 0)));
    }

    public int[] searchRange(int[] nums, int target) {

        if(nums == null || nums.length == 0){
            return new int []{-1,-1};
        }

        int any = getAny(nums, target, 0, nums.length-1);

        if(any == -1){

            return new int []{-1,-1};

        }else{

            int toLeft = any;
            int toRight = any;

            boolean goToLeft = true;
            boolean goToRight = true;

            while(goToLeft || goToRight){

                int newLeft = toLeft - 1;
                if(newLeft >= 0 && nums[newLeft]==target) {
                    toLeft = newLeft;
                }else{
                    goToLeft = false;
                }
                int newRight = toRight + 1;
                if(newRight<nums.length && nums[newRight]==target) {
                    toRight = newRight;
                }else{
                    goToRight = false;
                }
            }
            return new int []{toLeft, toRight};
        }
    }

    private int getAny(int[] nums, int target, int left, int right) {
        if(nums[left] > target){
            return -1;
        }
        if(nums[right] < target){
            return -1;
        }
        int middle = left + (right - left)/2;
        if(nums[middle] == target) return middle;
        if(nums[middle] > target){
            return  getAny(nums, target, left, middle);
        }else{
            return  getAny(nums, target, middle+1, right);
        }
    }

}
