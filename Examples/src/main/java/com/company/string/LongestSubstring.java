package com.company.string;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {


    public static void main(String[] args) {
        System.out.println(new LongestSubstring().lengthOfLongestSubstring("pwwkew"));

    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int max = 1;
        for (int i=0; i<chars.length-1; i++){
           Set<Character> set = new HashSet<>();
           set.add(chars[i]);
           for (int y = i+1; y < chars.length; y++){
               if(set.contains(chars[y])){
                   break;
               }else{
                   set.add(chars[y]);
                   int cLength = y - i+1;
                   if(max<cLength){
                       max= cLength;
                   }
               }
           }
        }
        return max;
    }
}
