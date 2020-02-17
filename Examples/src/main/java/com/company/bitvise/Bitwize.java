package com.company.bitvise;

public class Bitwize {


    public static void main(String[] args) {
        shiftRightMakingEvenNumber();

        //XOR qualities
        //task https://leetcode.com/explore/interview/card/microsoft/48/others/208/

        int x = 20;
        System.out.println("X = " + x +  " "+Integer.toBinaryString(x));

        int xoredByZero = x ^ 0; //makes no change to 20
        System.out.println("xoredByZero = " + xoredByZero +  " "+Integer.toBinaryString(xoredByZero));

        int xoredByItself = x ^ x;  //nullifies, the same like  * 0
        System.out.println("xoredByItself = " + xoredByItself +  " "+Integer.toBinaryString(xoredByItself));

        int xoredWithUniqie = (((2 ^ 1) ^ 3) ^ 2) ^ 1; //everything except unique number will be nullified
        System.out.println("xoredWithUniqie = " + xoredWithUniqie +  " "+Integer.toBinaryString(xoredWithUniqie));




    }

    private static void shiftRightMakingEvenNumber() {
        //some bitwises
        int lo=10;
        int fence=20;
        int shifted  = (lo + fence) >>> 1;  //like divide by 2, shifted right, and places from left filled by 0
        int shifted2  = (lo + fence) >> 1;  //like divide by 2

        System.out.println("shifted=" + shifted);
        System.out.println("shifted=" + Integer.toBinaryString(shifted));
        System.out.println("shifted2=" + Integer.toBinaryString(shifted2));

        int mid = shifted & ~1;  // ~1 == ...1110 , & with ~1 simply nullify the last 1, if it was 1 - that will make the number even
                                 // the & ~1 has no effect on even number, it is already has 0 at the end

        System.out.println("mid=" + mid);
        System.out.println("mid=" + Integer.toBinaryString(mid));
    }
}
