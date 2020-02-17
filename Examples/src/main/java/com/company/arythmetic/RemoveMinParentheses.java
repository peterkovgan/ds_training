package com.company.arythmetic;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class RemoveMinParentheses {

    //https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/solution/

    public String minRemoveToMakeValid(String s) {

        Set<Integer> indexesToRemove = new HashSet<>();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {       //iterate

            if (s.charAt(i) == '(') {                //add ( to stack
                stack.push(i);
            }
            if (s.charAt(i) == ')') {              //try to find an opening ( for the input )
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);          //remove ) if no matching ( exists
                } else {
                    stack.pop();                     //neutralize ) if matching ( exists
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop()); //remove all ( that did not find matching ) before
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {                     //re-inspect the string, removing all mismatches
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
