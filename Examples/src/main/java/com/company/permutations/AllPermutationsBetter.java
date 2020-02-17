package com.company.permutations;

import java.util.ArrayList;
import java.util.List;

public class AllPermutationsBetter {



    public static void main(String[] args) {
        int[] dist = new int[]{1, 2, 3};
        List<List<Integer>> result = new AllPermutationsBetter().permute(dist);
        for (List<Integer> list : result) {
            System.out.println(list.toString());
        }
    }


    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();

        boolean[] visited = new boolean[nums.length];

        List<Integer> reqBranchAnswer = new ArrayList<Integer>();

        helper(results, visited, nums, reqBranchAnswer);

        return results;
    }

    public void helper(List<List<Integer>> results, boolean[] visited, int[] nums, List<Integer> reqBranchAnswer) {

        if (reqBranchAnswer.size() == nums.length) {
            results.add(new ArrayList<Integer>(reqBranchAnswer));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (!visited[i]) {

                reqBranchAnswer.add(nums[i]);

                visited[i] = true;

                helper(results, visited,  nums, reqBranchAnswer);

                reqBranchAnswer.remove(reqBranchAnswer.size() - 1);

                visited[i] = false;

            }
        }
    }


}
