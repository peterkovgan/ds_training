package com.company.permutations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *   1)
 *   n! permutations
 *
 *   2)
 *   for r members in each permutation, while we have n members
 *   if the order is important, we have:
 *      n!
 *   --------
 *    (n-r)!
 *
 *   3)
 *   for r members in each permutation, while we have n members
 *   if the order is NOT important, we have:
 *      n!
 *   --------
 *    (n-r)! n!
 *
 *   4)
 *   If letters repeat
 *   Example:
 *   ALGEBRA
 *   2-A
 *   Number of unique permutations = N! / 2! = 7! / 2!
 *
 *   MISSISSIPPI
 *   I-4
 *   S-4
 *   P-2
 *   Number of unique permutations = N! / (4! * 4! * 2!)
 *
 *
 *
 *
 *
 *
 */


public class AllPermutations {

    public static void main(String[] args) {
        int[] dist = new int[]{1,2,3};
        List<List<Integer>> result = new AllPermutations().permute(dist);
        for (List<Integer> list : result) {
            System.out.println(list.toString());
        }
    }

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> rlist = new ArrayList<>();

        int controlArray [] = IntStream.range(0, nums.length).toArray(); //control array populated by indexes 0,1,2... - they will b e permuted

        int [] reqBranchArray = new int[nums.length];                    //permuted indexes will be stored in this array

        permutes(rlist, nums, controlArray, -1, reqBranchArray);

        return rlist;
    }

    private void permutes(List<List<Integer>> rlist, int[] nums, int controlArray [], int level, int [] reqBranchArray) {
        int newLevel = level + 1;
        if (newLevel == nums.length){
            //reqBranchArray - stores now a unique permutation of indexes
            rlist.add(IntStream.of(reqBranchArray).map(e->nums[e]).boxed().collect(Collectors.toList()));
            return;
        }

        for(Integer i : controlArray){  //repeat L times
            int newControlArray[] = new int[controlArray.length - 1];      //create shorter arrays of L-1 length: ..1.2.3; 0..,2,3; 0.1..3; 0.1.2..
            fillIt( newControlArray, controlArray, i );                    //fill shorter arrays by data, missing 1 element (first time it is missing 0)
            reqBranchArray[newLevel] = i;                                  //req branch arrays populated for every branch by different number
            permutes(rlist,nums,newControlArray,newLevel,reqBranchArray);  //the process continues, with lesser number of available indexes in the next cycle
        }
    }

    private void fillIt(int[] newControlArray, int[] controlArray, Integer i) {
        int u=0;
        for(int e:controlArray){
            if(e!=i){
                newControlArray[u] = e;
                u++;
            }
        }
    }


}
