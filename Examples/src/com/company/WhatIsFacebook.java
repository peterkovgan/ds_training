package com.company;

import java.util.HashSet;
import java.util.Set;

public class WhatIsFacebook {

    public static void main(String[] args) {
        //Given a string with no spaces that represents a sentence and a dictionary of words, split the string into a sentence with spaces:
        Set<String> dic = new HashSet<>();
        dic.add("i");
        dic.add("is");
        dic.add("what");
        dic.add("facebook");
        System.out.println(splitIt("whatisfacebook", dic, ""));
    }

    static String splitIt(String input, Set<String> dictionary, String result){
        if(input.equals("")){
            return result;
        }
        for(String word:dictionary){
            if(input.startsWith(word)){
                Set<String> childDic  = new HashSet<>(dictionary);
                childDic.remove(word);
                String childResult =  result+" "+word;
                String res  = splitIt(input.replaceFirst(word, ""), childDic, childResult);
                if(res!=null){
                    return res;
                }
            }
        }
        return null;
    }
}
