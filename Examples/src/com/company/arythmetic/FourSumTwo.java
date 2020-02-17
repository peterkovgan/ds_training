package com.company.arythmetic;

import java.util.*;

/**
 * How many different combinations exists in such a way, that elements of 4 arrays could provide sum = 0 :
 *
 * arr1[x]+arr1[y]+arr1[z]+arr1[k]==0
 *
 *  1,1,1,0
 *  2,2,2,0
 *  3,3,3,4
 * -4,3,3,3
 *
 */

public class FourSumTwo {

    public static void main(String[] args) {

        int a1[] = new int[]{1,2,3,4};
        int b1[] = new int[]{1,2,3,4};
        int c1[] = new int[]{1,2,3,4};
        int d1[] = new int[]{1,2,3,4};
        System.out.println(new FourSumTwo().fourSumCount(a1,b1,c1,d1));


    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        int count = 0;

        Map<Integer, Integer> findAllSums1 = allSums(A, B);

        Map<Integer, Integer> findAllSums2 = allSums(C, D);

        for(Integer num : findAllSums1.keySet()){

            int variants1 = findAllSums1.get(num);

            if(findAllSums2.containsKey(-num)){

                int variants2 = findAllSums2.get(-num);

                count += (variants1*variants2);

            }
        }

        return count;
    }

    private Map<Integer, Integer> allSums(int[] a, int[] b) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<a.length; i++){

            for(int y=0;y<b.length;y++){

                int sum = a[i] + b[y];

                if(map.containsKey(sum)){

                    map.put(sum, map.get(sum)+1);

                }else{

                    map.put(sum, 1);

                }
            }
        }

        return map;
    }
}
