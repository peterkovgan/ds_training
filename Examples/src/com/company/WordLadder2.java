package com.company;

import javafx.util.Pair;

import java.util.*;

public class WordLadder2 {

    /**
     * TC O(M*N), M-word length, N- number words (but 2-ce shorter than the first 1-direction variant)
     * SC O(M*N), M-word length, N- number words
     *
     */

    /**
     *
     * This is BFS from 2 ends

     Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

     Only one letter can be changed at a time.
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     Note:

     Return 0 if there is no such transformation sequence.
     All words have the same length.
     All words contain only lowercase alphabetic characters.
     You may assume no duplicates in the word list.
     You may assume beginWord and endWord are non-empty and are not the same.
     Example 1:

     Input:
     beginWord = "hit",
     endWord = "cog",
     wordList = ["hot","dot","dog","lot","log","cog"]

     Output: 5


     **/


    private int L;
    private HashMap<String, ArrayList<String>> allComboDict;

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        int ladder = new WordLadder2().ladderLength("hit","cog",wordList);
        System.out.println("ladder="+ladder);
    }

    WordLadder2(){
        this.L = 0;

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        this.allComboDict = new HashMap<String, ArrayList<String>>();
    }

    private int visitWordNode(
            Queue<Pair<String, Integer>> Q,
            HashMap<String, Integer> visited,
            HashMap<String, Integer> othersVisited) {

        Pair<String, Integer> node = Q.remove();

        String word = node.getKey();
        int level = node.getValue();

        for (int i = 0; i < this.L; i++) {

            // Intermediate words for current word
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

            // Next states are all the words which share the same intermediate state.
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
                // If at any point if we find what we are looking for
                // i.e. the end word - we can return with the answer.
                if (othersVisited.containsKey(adjacentWord)) {
                    return level + othersVisited.get(adjacentWord);
                }

                if (!visited.containsKey(adjacentWord)) {

                    // Save the level as the value of the dictionary, to save number of hops.
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length.
        this.L = beginWord.length();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        ArrayList<String> transformations =
                                this.allComboDict.getOrDefault(newWord, new ArrayList<String>());
                        transformations.add(word);
                        this.allComboDict.put(newWord, transformations);
                    }
                });

        // Queues for birdirectional BFS
        // BFS starting from beginWord
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<Pair<String, Integer>>();
        // BFS starting from endWord
        Queue<Pair<String, Integer>> Q_end = new LinkedList<Pair<String, Integer>>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));

        // Visited to make sure we don't repeat processing same word.
        HashMap<String, Integer> visitedBegin = new HashMap<String, Integer>();
        HashMap<String, Integer> visitedEnd = new HashMap<String, Integer>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

            // One hop from begin word
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }

            // One hop from end word
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }

        return 0;
    }
}
