package com.company.arythmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueQuadruples {




    public static void main(String[] args) {
        int array[] = new int[]{-1, -1, 2, 5,2,4,5,6,-1,-3 };
        List<List<Integer>> result = new UniqueQuadruples().fourSum(array, 4);
        for (List<Integer> el : result) {
            for (Integer e : el) {
                System.out.print(e);
                System.out.print(" ");
            }
            System.out.println("");
        }

    }

    public List<List<Integer>> fourSum(int[] nums, int target) {

        //sort , because in summary questions, you can "see the future" and eliminate certain loops, if you go in ascending order
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int y = 0; y < nums.length; y++) {

            int newTarget = target - nums[y];
            if (y > 0 && nums[y - 1] == nums[y]) {
                continue;
            }

            for (int a = y + 1; a < nums.length; a++) {

                //if (nums[a] > newTarget) {//clear that with this A further B and C will do only worse, because they are greater
                //    break;
                //}
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
                if (a > y + 1 && nums[a - 1] == nums[a]) {
                    continue;
                }

                int b = a + 1;
                int c = nums.length - 1;

                while (b < c) {
                    int sum = nums[a] + nums[b] + nums[c];
                    if (sum < newTarget) {
                        b++;
                    } else if (sum > newTarget) {
                        c--;
                    } else {
                        List row = new ArrayList();
                        row.add(nums[y]);
                        row.add(nums[a]);
                        row.add(nums[b]);
                        row.add(nums[c]);
                        result.add(row);
                        while (b < c && nums[b] == nums[b + 1]) {
                            b++;
                        }
                        if (b < c && nums[c] == nums[c - 1]) {
                            c--;
                        }
                        b++;
                        c--;
                    }
                }
            }

        }


        return result;
    }

}
