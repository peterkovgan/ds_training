package com.company.recursion;

import java.util.*;

public class ConstructWordGivenDice {


    //Construct a word using dice
    public static void main(String[] args) {
        //Case 1 - available
        possibleToReachWordTest();
        //Case 2  - unavailable:
        impossibleToReachWordTest();
    }

    private static void impossibleToReachWordTest() {
        Map<Integer, List<Character>> input = new HashMap<>();

        List<Character> l1 = new ArrayList<>();
        List<Character> l2 = new ArrayList<>();
        List<Character> l3 = new ArrayList<>();
        List<Character> l4 = new ArrayList<>();
        List<Character> l5 = new ArrayList<>();

        input.put(0, l1);
        input.put(1, l2);
        input.put(2, l3);
        input.put(3, l4);
        input.put(4, l5);

        l1.add('a');
        l1.add('u');
        l1.add('r');
        l1.add('m');
        l1.add('n');

        l2.add('a');
        l2.add('h');
        l2.add('r');
        l2.add('m');
        l2.add('t');

        l3.add('a');
        l3.add('l');
        l3.add('r');
        l3.add('y');
        l3.add('w');

        l4.add('a');
        l4.add('k');
        l4.add('o');
        l4.add('m');
        l4.add('n');

        l5.add('a');
        l5.add('k');
        l5.add('l');
        l5.add('y');
        l5.add('n');

        System.out.println(new ConstructWordGivenDice().possible("hello", input));
    }

    private static void possibleToReachWordTest() {
        Map<Integer, List<Character>> input = new HashMap<>();

        List<Character> l1 = new ArrayList<>();
        List<Character> l2 = new ArrayList<>();
        List<Character> l3 = new ArrayList<>();
        List<Character> l4 = new ArrayList<>();
        List<Character> l5 = new ArrayList<>();

        input.put(0, l1);
        input.put(1, l2);
        input.put(2, l3);
        input.put(3, l4);
        input.put(4, l5);

        l1.add('a');
        l1.add('e');
        l1.add('r');
        l1.add('m');
        l1.add('n');

        l2.add('a');
        l2.add('h');
        l2.add('r');
        l2.add('m');
        l2.add('t');

        l3.add('a');
        l3.add('l');
        l3.add('r');
        l3.add('y');
        l3.add('w');

        l4.add('a');
        l4.add('k');
        l4.add('o');
        l4.add('m');
        l4.add('n');

        l5.add('a');
        l5.add('k');
        l5.add('l');
        l5.add('y');
        l5.add('n');

        System.out.println(new ConstructWordGivenDice().possible("hello", input));
    }

    //Whole TC: (throws * wordLength) + (wordLength ^ wordLength)
    //Whole SC: (throws number  * wordLength) + word length
    static boolean possible(String word, Map<Integer, List<Character>> input) {
        //Letter = > Set of {Throws of dice numbers}
        Map<Character, Set<Integer>> verticesStorage = new HashMap<>();
        //Prepare
        //TC - O (n), where N is throws * wordLength
        //SC - O (n), where N is throws number  * wordLength
        for (Integer inputIndex : input.keySet()) {
            List<Character> diceThrow = input.get(inputIndex);
            for (Character toFind : word.toCharArray()) {
                if (diceThrow.contains(toFind)) {
                    Set<Integer> throwsWithThatChar = verticesStorage.get(toFind);
                    if (throwsWithThatChar == null) {
                        throwsWithThatChar = new HashSet<>();
                        verticesStorage.put(toFind, throwsWithThatChar);
                    }
                    throwsWithThatChar.add(inputIndex);
                }
            }
        }

        char[] wordInChars = word.toCharArray();


        //TC:
        //Worst case
        //recursion branches could be    N = word length
        //recursion depth could be also  N = word length
        //This TC O (word length ^ word length) - exponential
        //SC:
        //recursion adds this SC O (word length)
        return req(wordInChars, verticesStorage, 0, wordInChars.length - 1, new HashSet<Integer>());
    }

    private static boolean req(char[] wordInChars, Map<Character, Set<Integer>> verticesStorage, int currentIndex, int maxIndex, Set<Integer> usedThrows) {
        char currentChar = wordInChars[currentIndex];
        Set<Integer> throwsNumbers = verticesStorage.get(currentChar);
        if (throwsNumbers == null) {//no more throws with required char
            return false;
        }
        //locally available throws
        Set<Integer> availableThrows = new HashSet(throwsNumbers);
        availableThrows.removeAll(usedThrows);

        if (availableThrows.size() > 0) {
            //The end of the word reached
            if (currentIndex == maxIndex) {
                return true;
            }

            //continue  - with other remaining throws for the current char
            for (Integer throwNumber : availableThrows) {
                Set<Integer> usedInBranch = new HashSet(usedThrows);
                usedInBranch.add(throwNumber);
                if (req(wordInChars, verticesStorage, currentIndex + 1, maxIndex, usedInBranch)) {
                    return true;
                }
            }

        } else {
            return false;
        }

        return false;

    }


}
