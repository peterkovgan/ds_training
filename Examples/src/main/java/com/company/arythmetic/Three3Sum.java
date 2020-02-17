package com.company.arythmetic;

import java.util.*;
import java.util.stream.Collectors;

public class Three3Sum {

    /**
     *
     * Given an array nums of n integers,
     * are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     *
     * https://leetcode.com/problems/3sum/
     */

    public static void main(String[] args) {
        int array[] = new int[]{-1, -1, 2 };
        List<List<Integer>> result = new Three3Sum().threeSum(array);
        for (List<Integer> el : result) {
            for (Integer e : el) {
                System.out.print(e);
                System.out.print(" ");
            }
            System.out.println("");
        }

    }


    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        Map<Integer, Set<Integer>> indexMap = new HashMap<>();

        //O(n)
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> indexSet = indexMap.get(nums[i]);
            if (indexSet == null) {
                indexSet = new HashSet<>();
                indexMap.put(nums[i], indexSet);
            }
            indexSet.add(i);
        }

        class ArrayWrapper {

            int a;
            int b;
            int c;

            List<Integer> l;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ArrayWrapper that = (ArrayWrapper) o;
                return a == that.a &&
                        b == that.b &&
                        c == that.c;
            }

            @Override
            public int hashCode() {
                return Objects.hash(a, b, c);
            }

            ArrayWrapper(int one, int two, int three) {
                a = one;
                b = two;
                c = three;
                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                if (b > c) {
                    int temp = b;
                    b = c;
                    c = temp;
                }
                if (a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                l = new ArrayList<>();
                l.add(a);
                l.add(b);
                l.add(c);
            }


            List<Integer> toList() {
                return l;
            }


        }

        Set<ArrayWrapper> triples = new HashSet<>();

        for (int a = 0; a < nums.length; a++) {
            if(nums[a] > 0){//ignore positives at all
                break;
            }
            if (a > 0 && nums[a - 1] == nums[a]) {//two numbers aside and they are duplicates, avoid duplicates
                continue;
            }
            for (int b = a + 1; b < nums.length; b++) {
                if (b != a) {//not interested in duplicates
                    int numberToFind = 0 - (nums[a] + nums[b]); //look for the number that nulifies the sum of 2
                    if (!indexMap.containsKey(numberToFind)) {
                        continue;
                    }
                    ArrayWrapper t = new ArrayWrapper(nums[a], nums[b], numberToFind);
                    if (triples.contains(t)) {//avoid duplications
                        continue;
                    }
                    int c = complementIndex(numberToFind, indexMap, new int[]{a, b});
                    if (c != -1) {
                        triples.add(t);
                    }
                }
            }
        }




        return triples.stream().map(o -> o.toList()).collect(Collectors.toList());

    }

    private int complementIndex(int numberToFind, Map<Integer, Set<Integer>> indexMap, int avoid[]) {
        Set<Integer> indices = indexMap.get(numberToFind);
        if (indices == null) {
            return -1;
        } else {
            for (Integer index : indices) {
                if (index != avoid[0] && index != avoid[1]) {
                    return index;
                }
            }
            return -1;
        }
    }

}
