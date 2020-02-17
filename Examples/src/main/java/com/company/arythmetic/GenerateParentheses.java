package com.company.arythmetic;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    /**
     * https://leetcode.com/problems/generate-parentheses/
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     * For example, given n = 3, a solution set is:
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     *
     */


    public static void main(String[] args) {
        List<String> result = (new GenerateParentheses()).generateParenthesis(3);
        for (String res : result) {
            System.out.println(res);
        }
    }

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return null;
        }

        if (n == 1) {
            List result = new ArrayList<>();
            result.add("()");
            return result;
        }

        List<String> listOfVariants = new ArrayList<>();
        int balance = 1;
        char[] array = new char[n*2];
        int index= 0;
        array[index]='(';
        createVariants(listOfVariants, n, n, true, array , index,  balance);
        return listOfVariants;
    }


    private void createVariants(List<String> listOfVariants, int toRightCount, int toLeftCount, boolean toRight, char[] array, int index, int balance) {

        int localToRightCount = toRightCount;
        int localToLeftCount  = toLeftCount;

        if (toRight) {
            localToRightCount -= 1;
        } else {
            localToLeftCount -= 1;
        }

        boolean completed = true;

        int newIndex = index+1;

        int localBalance = balance;

        if (localToRightCount > 0) {
            localBalance++;
            array[newIndex] = '(';
            createVariants(listOfVariants, localToRightCount, localToLeftCount, true, array, newIndex, localBalance);
            completed = false;
        }

        localBalance = balance;

        if (localToLeftCount > 0 && localBalance > 0) {
            localBalance--;
            array[newIndex] = ')';
            createVariants(listOfVariants, localToRightCount, localToLeftCount, false, array, newIndex, localBalance);
            completed = false;
        }

        if (completed && balance==0) {
            listOfVariants.add(String.valueOf(array));
        }
    }


}
