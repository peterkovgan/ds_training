package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PhoneCombinationsMultithreaded {

    public static void main(String[] args) {
         String str = "25";
         List<String> result =  new PhoneCombinationsMultithreaded().letterCombinations(str);
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

        PTask task = new PTask(digits, 0, "");
        task.fork();
        List<String> res = task.join();
        return res;
    }

    class PTask extends RecursiveTask<List<String>> {
        final String digits;
        final int level;
        final String passed;


        PTask(String digits, int level, String passed){
            this.digits = digits;
            this.level = level;
            this.passed = passed;
        }

        @Override
        protected List<String> compute() {
            List<String> result = new ArrayList<>();
            Character ch = digits.charAt(level);
            String combos = lettersCombinations.get(ch);
            char children[] = combos.toCharArray();
            int newLevel = level+1;
            if(newLevel >= digits.length()){
                for(Character c: children) {
                    String localPassed = passed + c;
                    result.add(localPassed);
                }
            }else{

                for(Character c: children) {
                    String localPassed = passed + c;
                    PTask task = new PTask(digits,newLevel,localPassed);
                    task.fork();
                    List<String> res = task.join();
                    result.addAll(res);
                }
            }
            return result;
        }
    }


}
