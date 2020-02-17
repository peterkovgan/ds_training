package com.company.permutations;

public class BestPermutation {

    int count;

    public static void main(String[] args) {
        new BestPermutation().permutation("abcde");
    }

    void permutation(String str) {
         permutation(str, "");
    }

    void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println((count++)+" :"+prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                 String rem = str.substring(0, i) + str.substring ( i + 1);
                 permutation(rem, prefix + str.charAt( i));
            }
        }
    }

}
