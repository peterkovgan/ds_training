package com.company.palindromes;

public class PalindromePermutation {

    public static void main(String[] args) {

        String input = "nseensuud";

        boolean answer = isStringPalindromePermutation(input);

        System.out.println("Result = " + answer);

    }

    private static boolean isStringPalindromePermutation(String input) {

        if(input == null) return false;

        if(input.isEmpty()) return false;

        int checker = 0;

        for (int i = 0; i < input.length(); i++) {

            int character = input.charAt(i) - 'a';

            int oneShiftedByNumberInCharacter = 1 << character;  //that marks by 1 a place of this char in the bit vector

            int summaryAnd = checker & oneShiftedByNumberInCharacter;  //checker has 0 in this place (the letter appears 1-st time,
                                                                       //then we are OK

            if ( summaryAnd > 0 ) {                                   //the letter appears 2-d time (it might be OK , because palindrome is a mirrored thing...

                int revertToShiftedByChar = ~oneShiftedByNumberInCharacter;  //put 0 in the place of the letter (in order to 0-fy this place in the checker later)
                checker = checker & revertToShiftedByChar;                   //0-fy the checker in this place
            } else {
                checker |= oneShiftedByNumberInCharacter;                    //put 1-st, 3-d, 5-th, etc letter mark in the checker
            }
        }

        if ( input.length() % 2 == 0 ) {    //even number of letters
            if ( checker == 0) {            //no marks remained
                return true;                //so it is a palindrome
            }
            else return false;               //otherwise - not a palindrome
        } else {                             //odd number of letters, we expect 1 only once in the number
            //Better solution:
            int bc = Integer.bitCount(checker);
            if(bc == 1){
                return true;
            }else{
                return false;
            }
        }

    }

}
