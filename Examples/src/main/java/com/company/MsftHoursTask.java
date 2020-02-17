package com.company;

import java.util.*;

public class MsftHoursTask {
    public static void main(String[] args) {
        int res = MsftHoursTask.solution(1, 2, 3, 4);
        System.out.println(res);


    }

    public static int solution(int A, int B, int C, int D) {
        int[] dist = new int[]{A, B, C, D};
        for (int d : dist) {
            if (d < 0) return 0;
            if (d > 9) return 0;
        }
        List<List<Integer>> result = permute(dist);
        Set<Clock> clocks = new HashSet<>();
        for (List<Integer> ints : result) {
            int h1 = ints.get(0);
            int h2 = ints.get(1);
            int m1 = ints.get(2);
            int m2 = ints.get(3);
            clocks.add(new Clock(h1, h2, m1, m2));
        }
        int count = 0;
        for (Clock clock : clocks) {
            if (clock.isValid()) {
                count++;
            }
        }
        return count;
    }

    static class Clock {
        int h1;
        int h2;
        int m1;
        int m2;

        public Clock(int h1, int h2, int m1, int m2) {
            this.h1 = h1;
            this.h2 = h2;
            this.m1 = m1;
            this.m2 = m2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Clock clock = (Clock) o;
            return h1 == clock.h1 &&
                    h2 == clock.h2 &&
                    m1 == clock.m1 &&
                    m2 == clock.m2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(h1, h2, m1, m2);
        }

        public boolean isValid() {
            String hourStr = h1 + "" + h2;
            String minuteStr = m1 + "" + m2;
            int hours = Integer.valueOf(hourStr);
            int minutes = Integer.valueOf(minuteStr);
            if ((hours >= 0) && (hours <= 23) && (minutes >= 0) && (minutes <= 59)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "Clock{" + h1 + h2 + ":" + m1 + m2 + '}';
        }
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answers = new ArrayList<>();
        boolean[] visitedNodes = new boolean[nums.length];
        helper(nums, new ArrayList<Integer>(), answers, visitedNodes);
        return answers;
    }

    private static void helper(int[] nums, List<Integer> subanswers, List<List<Integer>> answers, boolean[] visitedNodes) {
        if (subanswers.size() == nums.length) {
            answers.add(new ArrayList<Integer>(subanswers));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (!visitedNodes[j]) {
                subanswers.add(nums[j]);
                visitedNodes[j] = true;
                helper(nums, subanswers, answers, visitedNodes);
                subanswers.remove(subanswers.size() - 1);
                visitedNodes[j] = false;
            }
        }
    }


}
