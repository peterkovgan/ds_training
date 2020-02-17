package com.company.permutations;

public class PermutationsWithNoDups {

    // Driver code
    public static void main(String[] args) {

        char str[] = {'A', 'B', 'C', 'A'};
        int n = str.length;
        findPermutations(str, 0, n);
    }

    // Prints all distinct permutations in str[0..n-1]
    static void findPermutations(char str[], int index, int length) {
        if (index >= length) {
            System.out.println(str);
            return;
        }

        for (int i = index; i < length; i++) {                        //start from 0, on next req layer it will be 1, etc

            // Proceed further for str[i] only if it
            // doesn't match with any of the characters
            // after str[index]
            boolean check = shouldSwap(str, index, i);

            if (check) {
                swap(str, index, i);
                findPermutations(str, index + 1, length);
                swap(str, index, i);
            }
        }
    }

    // Returns true if str[curr] does not matches with any of the
    // characters after str[start]
    static boolean shouldSwap(char str[], int start, int curr) {
        for (int i = start; i < curr; i++) {
            if (str[i] == str[curr]) {
                return false;
            }
        }
        return true;
    }



    static void swap(char[] str, int i, int j) {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }



}
