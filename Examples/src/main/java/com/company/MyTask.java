package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MyTask {


    static class Interval{
        int start;
        int end;

        Interval(int start, int end){
            this.start= start;
            this.end = end;
        }
    }



    static int minRooms(Interval [] intervals){

        int size = intervals.length;
        //sorted by start date
        Comparator<Interval> compare = (o1, o2) -> o1.start - o2.start;

        Arrays.sort(intervals,compare);


        Comparator<Integer> compareEndDates = (o1, o2) -> o1 - o2;

        //heap of min end date
        PriorityQueue<Integer> meanHeap = new PriorityQueue<>(size, compareEndDates);
        meanHeap.add(intervals[0].end);

        for(int i=1; i < intervals.length;i++){
            Integer end = meanHeap.peek();
            if(end < intervals[i].start){
                meanHeap.poll();
            }
            meanHeap.add(intervals[i].end);
        }

        return meanHeap.size();

    }





    public static void main(String[] args) {

        Interval i1 = new Interval(0,2);
        Interval i2 = new Interval(0,3);
        Interval i3 = new Interval(1,4);
        Interval i4 = new Interval(6,7);

        //0  1   2   3   4
        //--------
        //------------
        //   -------------
        //                       -----

        int rooms =  minRooms(new Interval[]{i1, i2, i3, i4});

        System.out.println(rooms);

    }





}
