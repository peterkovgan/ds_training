package com.company.intervals;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class IntervalsMerge {


    /**
     * Create Interval objects (start, end)
     * Sort interval objects , adding them to List (may be ArrayList) in the order of start (sort by start)
     * Create a new LinkedList (merged)
     * Add from sorted list to merge list , merging on the way
     * Compared merged.getLast().end and interval.start
     * And
     * merged.getLast().end and interval.end
     */

    private class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
        }
    }

    public int[][] merge(int[][] intervals) {

        if (intervals == null) return new int[0][0];

        int width = intervals.length;

        if (width == 0) return new int[0][0];

        if (width == 1) return intervals;

        int height = intervals[0].length;

        List<Interval> ints = new LinkedList<>();
        for (int x = 0; x < width; x++) {
            ints.add(new Interval(intervals[x][0], intervals[x][1]));
        }
        Collections.sort(ints, new IntervalComparator());
        LinkedList<Interval> merged = new LinkedList<Interval>();

        for (Interval interval : ints) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }

        int[][] result = new int[merged.size()][2];

        int i = 0;
        for (Interval interval : merged) {
            result[i][0] = interval.start;
            result[i][1] = interval.end;
            i++;
        }

        return result;
    }

}
