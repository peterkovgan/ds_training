package com.company;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

    /**
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     *
     *
     */

    public static void main(String[] args) {
        //"MCMXCIV" 1994
        System.out.println(" Number : " + new RomanToInt().romanToInt("MCMXCIV"));
    }

    Map<Character, Integer> map ;

    public int romanToInt(String s) {
         if(s==null || s.length()==0) return 0;
         map = new HashMap<>();
         map.put('I', 1);
         map.put('V', 5);
         map.put('X', 10);
         map.put('L', 50);
         map.put('C', 100);
         map.put('D', 500);
         map.put('M', 1000);
         int result = 0;
         char charBefore = 'P';
         for(int i = s.length() - 1; i>=0; i--){
             char c = s.charAt(i);
             int in = map.get(c);
             int reduce = 0;
             if(charBefore=='V' && c=='I'){
                 reduce = 1;
             }else if(charBefore=='X'  && c=='I'){
                 reduce = 1;
             }else if(charBefore=='L'  && c=='X'){
                 reduce = 10;
             }else if(charBefore=='C'  && c=='X'){
                 reduce = 10;
             }else if(charBefore=='D'  && c=='C'){
                 reduce = 100;
             }else if(charBefore=='M'  && c=='C'){
                 reduce = 100;
             }else{
                 result+=in;
             }
             if(reduce!=0){
                 result-=reduce;
             }
             charBefore = c;
         }

         return result;
    }

}
