package com.company.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates)
 * and a target number (target), find all unique combinations
 * in candidates where the candidate numbers sums to target.
 *
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */


public class CombinationSum {

    public static void main(String[] args) {

        int[] candidates = new int[]{2, 3, 6, 7};

        int target = 7;
        List<List<Integer>> result = (new CombinationSum()).combinationSum(candidates, target);
        result.stream().forEach(e -> System.out.println(e));

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        reqCollector(result, candidates, target, 0, new ArrayList<Integer>(), 0);
        return result;
    }


    private void reqCollector(final List<List<Integer>> result, final int[] candidates, final int target, final int currentSum, final List<Integer> currentCollection, final int start) {

        if (currentSum == target) {
            result.add(new ArrayList(currentCollection));
            return;
        }
        if (currentSum > target) {
            return;
        }

        for (int i = start; i < candidates.length && currentSum + candidates[i] <= target; i++) {
            currentCollection.add(candidates[i]);
            reqCollector(result, candidates, target, currentSum + candidates[i], currentCollection, i);
            currentCollection.remove(currentCollection.size() - 1);
        }
    }

}
