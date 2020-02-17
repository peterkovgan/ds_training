package com.company.arythmetic;

import java.util.*;

public class Three3SumBetter {

    /**
     *
     * Given an array nums of n integers,
     * are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     *
     * https://leetcode.com/problems/3sum/
     */

    public static void main(String[] args) {
        int array[] = new int[]{0, 0, 0 };
        List<List<Integer>> result = new Three3SumBetter().threeSum(array);
        for (List<Integer> el : result) {
            for (Integer e : el) {
                System.out.print(e);
                System.out.print(" ");
            }
            System.out.println("");
        }

    }


    public List<List<Integer>> threeSum(int[] nums) {
        //sort , because in summary questions, you can "see the future" and eliminate certain loops, if you go in ascending order
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int a = 0; a < nums.length; a++) {

            //not obligatory, works because 0 is the target
            if(nums[a] > 0){//clear that with this A further B and C will do only worse, because they are greater
                break;
            }
            //I skip this A, if the previous A was the same
            //the reason is simple, such A already considered, re-incarnation of it will lead to a possible duplicate
            //the same processed in b and c below (in "while they repeat, skip them")
            //the logic behind that is that a , b and c are always unique, to avoid duplicates
            //duplicate (like -1 -1 2 ) is valid, but then a=-1, b=-1, c=2
            //that means each duplicate pointed by the unique variable
            //order we did in the beginning helps here too.
            //the "minimal elements" case (3 elements) like -1 -1 2 will lead to unique variable assignment
            //the "more elements" case (N elements) like -1 -1 -1 -1 2 will lead to unique variable assignment, only, if you skip already processed by each variable.
            //thus works following : "a was -1, let's never assign -1 to a, because we already done with that variant"
            //thus works following : "b was -1, let's never assign -1 to b, because we already done with that variant"
            //thus works following : "c was -1, let's never assign -1 to c, because we already done with that variant"
            //so only unique combinations collected
            if (a > 0 && nums[a - 1] == nums[a]) {
                continue;
            }

            int b = a+1;
            int c = nums.length-1;

            while(b < c){
                int sum = nums[a]+nums[b]+nums[c];
                if(sum < 0){
                    b++;
                }else if (sum>0){
                    c--;
                }else{
                    List row = new ArrayList();
                    row.add(nums[a]);
                    row.add(nums[b]);
                    row.add(nums[c]);
                    result.add(row);
                    while(b<c && nums[b]==nums[b+1]){
                        b++;
                    }
                    if(b<c &&  nums[c]==nums[c-1]){
                        c--;
                    }
                    b++;
                    c--;
                }
            }
        }
        return result;
    }
}
