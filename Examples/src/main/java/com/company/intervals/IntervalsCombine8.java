package com.company.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * https://leetcode.com/problems/meeting-rooms-ii/solution/
 *
 *
 * Time Complexity: O(N log N).
 *
 * There are two major portions that take up time here.
 * One is sorting of the array that takes O(N log N)
 * considering that the array consists of N elements.
 *
 * Then we have the min-heap. In the worst case, all N meetings will collide with each other.
 *
 * In any case we have N add operations on the heap.
 * In the worst case we will have
 * N extract-min operations as well.
 * Overall complexity being (N log N) since extract-min operation on a heap takes O ( log N).
 *
 *
 * Space Complexity: O(N) because we construct the min-heap and that can contain N elements
 * in the worst case as described above in the time complexity section.
 * Hence, the space complexity is O(N).
 *
 * Algorithm
 *
 * Sort the given meetings by their start time.
 * Initialize a new min-heap and add the first meeting's ending time to the heap.
 * We simply need to keep track of the ending times as that tells us when a meeting room will get free.
 *
 * For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
 *
 * If the room is free (end time <= start time of next), then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
 *
 * If not(end time > start time of next), then we allocate a new room and add it to the heap.
 *
 * After processing all the meetings, the size of the heap will tell us the number of rooms allocated. This will be the minimum number of rooms needed to accommodate all the meetings.
 *
 *
 */

public class IntervalsCombine8 {

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    public int minMeetingRooms(Interval[] intervals) {

        Arrays.sort( intervals , (a,b)->(a.start - b.start) );

        PriorityQueue<Interval> pq = new PriorityQueue<>( (a,b) -> (a.end-b.end) );

        for(Interval interval:intervals){
            if(!pq.isEmpty() && pq.peek().end <= interval.start){
                pq.poll();
            }
            pq.add(interval);
        }

        return pq.size();
    }

    public int minMeetingRooms2(Interval[] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
        PriorityQueue<Integer> allocator =
                new PriorityQueue<Integer>(
                        intervals.length,
                        new Comparator<Integer>() {
                            public int compare(Integer a, Integer b) {
                                return a - b;
                            }
                        });

        // Sort the intervals by start time
        Arrays.sort(
                intervals,
                new Comparator<Interval>() {
                    public int compare(Interval a, Interval b) {
                        return a.start - b.start;
                    }
                });

        // Add the first meeting
        allocator.add(intervals[0].end);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i].start >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i].end);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }

}
