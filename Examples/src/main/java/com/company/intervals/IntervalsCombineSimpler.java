package com.company.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * https://leetcode.com/problems/meeting-rooms-ii/solution/
 *
 * Algorithm
 *
 * Separate out the start times and the end times in their separate arrays.
 * Sort the start times and the end times separately. Note that this will mess up the original
 * correspondence of start times and end times. They will be treated individually now.
 * We consider two pointers: s_ptr and e_ptr which refer to start pointer and end pointer.
 * The start pointer simply iterates over all the meetings and the end pointer helps us track
 * if a meeting has ended and if we can reuse a room.
 * When considering a specific meeting pointed to by s_ptr, we check if this start
 * timing is greater than the meeting pointed to by e_ptr. If this is the case then
 * that would mean some meeting has ended by the time the meeting at s_ptr had to start.
 * So we can reuse one of the rooms. Otherwise, we have to allocate a new room.
 * If a meeting has indeed ended i.e. if start[s_ptr] >= end[e_ptr], then we increment e_ptr.
 * Repeat this process until s_ptr processes all of the meetings.
 *
 *  Time Complexity: O(NlogN) because all we are doing is sorting the two arrays for start timings
 *  and end timings individually and each of them would contain NN elements considering there are N intervals.
 *
 *  Space Complexity: O(N) because we create two separate arrays of size N,
 *  one for recording the start times and one for the end times.
 *
 */

public class IntervalsCombineSimpler {

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

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }

        // Sort the intervals by end time
        Arrays.sort(
                end,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return a - b;
                    }
                });

        // Sort the intervals by start time
        Arrays.sort(
                start,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return a - b;
                    }
                });

        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;

        // Variables to keep track of maximum number of rooms used.
        int usedRooms = 0;

        // Iterate over intervals.
        while (startPointer < intervals.length) {

            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            usedRooms += 1;
            startPointer += 1;

        }

        return usedRooms;
    }

}
