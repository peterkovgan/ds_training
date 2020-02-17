package com.company.palindromes;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Easy check of the palindrome
 */
public class TestPalindromeStackQueue {

    public static void main(String[] args) {

        boolean pld =  isPalindrome("radar");
        System.out.println(pld);

    }

    private static boolean isPalindrome(String radar) {

        Stack<Character> st = new Stack<>();
        Queue<Character> queue  = new ArrayDeque<>();

        for(Character c: radar.toCharArray()){
            st.add(c);
            queue.add(c);
        }

        for(int i=0; i<radar.length(); i++){
            Character c1 = st.pop();
            Character c2 = queue.poll();
            if(c1!=c2){
                return false;
            }
        }

        return true;

    }
}
