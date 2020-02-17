package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    public static void main(String[] args) {
        System.out.println("Radix sort in Java");
        int[] input = {9, 181, 51, 11, 33, 11, 39, 60, 2, 27, 24, 12};

        System.out.println("An Integer array before sorting");
        System.out.println(Arrays.toString(input));

        // sorting array using radix Sort Algorithm
        radixSort(input);

        System.out.println("Sorting an int array using radix sort algorithm");
        System.out.println(Arrays.toString(input));
    }

    public static void radixSort(int[] input) {
        final int RADIX = 10;

        // declare and initialize bucket[]
        List<Integer>[] bucket = new ArrayList[RADIX];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }

        // sort
        boolean maxLength = false;
        int tmp = -1, placement = 1;

        while ( !maxLength ) {
            maxLength = true;

            // split input between lists
            for (Integer i : input) {          //
                tmp = i / placement;           //181
                bucket[tmp % RADIX].add(i);    //bucket[1].add(181)
                if (maxLength && tmp > 0) {    //true && 181>0
                    maxLength = false;         //maxLength = false; Means, at least 1 number was relevant for that (tmp % RADIX) or was long enough to be processed
                                               //if that happened, we need continue shift left, if not - while looping will stop
                }
            }

            // empty lists into input array
            int a = 0;

            for (int b = 0; b < RADIX; b++) {  // 0...9
                for (Integer i : bucket[b]) {  // iterate over integers in bucket 0, then over integers in bucket 1, etc...
                    input[a++] = i;            //
                }
                bucket[b].clear();             //clean the bucket
            }

            // move to next digit
            placement *= RADIX;
        }
    }

}
