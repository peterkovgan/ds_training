package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneCombinations {

    public static void main(String[] args) {
         String str = "25";
         List<String> result =  new PhoneCombinations().letterCombinations(str);
         System.out.println(result.toString());
    }

    private static Map<Character, String> lettersCombinations = new HashMap<>();

    static {
        lettersCombinations.put('2', "abc");
        lettersCombinations.put('3', "def");
        lettersCombinations.put('4', "ghi");
        lettersCombinations.put('5', "jkl");
        lettersCombinations.put('6', "mno");
        lettersCombinations.put('7', "pqrs");
        lettersCombinations.put('8', "tuv");
        lettersCombinations.put('9', "wxyz");
    }



    public List<String> letterCombinations(String digits) {

        if(digits==null || digits.length()==0){
            return new ArrayList<String>();
        }

        List<String> strings = new ArrayList<>();

        req(strings, digits, 0, "");

        return strings;

    }

    private void req(List<String> strings, String digits, int level, String passed) {
        Character ch = digits.charAt(level);
        String combos = lettersCombinations.get(ch);
        char children[] = combos.toCharArray();
        level++;
        for(Character c: children){
            String localPassed = passed+c;
            if(level >= digits.length()){
                strings.add(localPassed);
            }else{
                req(strings,digits,level,localPassed);
            }
        }
    }
}
