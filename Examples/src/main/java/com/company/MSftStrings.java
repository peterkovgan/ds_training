package com.company;

import java.util.ArrayList;
import java.util.List;

public class MSftStrings {

    public static void main(String[] args) {
        System.out.println(solution("100000","99999a"));
    }

    public static boolean solution(String S, String T) {
        if(S==null || T==null) return false;
        //I assume those are two empty documents
        if(S.length()==0 || T.length()==0) return true;

        String one = convert(S);
        String two = convert(T);
        if(one.length() != two.length()){
            return false;
        }else{
            char[] first = one.toCharArray();
            char[] second = two.toCharArray();
            for(int i=0; i<first.length; i++){
                if(first[i]!=second[i] ){
                    if(first[i]!='*' && second[i]!='*'){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static String convert(String s){

        StringBuilder result = new StringBuilder();

        List<Integer> digits = new ArrayList<>();

        char chars[] = s.toCharArray();

        for(int i=0; i < chars.length; i++){
            char c = chars[i];
            if(Character.isDigit(c)){
                int startDig = i;
                int endDig = i;
                String numberString = "" + c;

                /**
                 * Warning!
                 *
                 * I assume here:
                 *
                 * you do not put 2 max 100000 numbers one next to other...
                 *
                 * in such case I need fix this function
                 *
                 * But you stated that the length of text is max 100000 before processing
                 *
                 * So , I assume no such case happens.
                 *
                 * If I have time from the 3-d task, I will get back here...
                 *
                 * */

                if(i < s.length() - 1){
                    for(int y=startDig+1; y<s.length(); y++){
                        if(Character.isDigit( chars[y])) {
                            endDig = y;
                            numberString = numberString + chars[y];
                        }else{
                            break;
                        }
                    }
                }

                int number = Integer.valueOf(numberString);
                i = endDig;
                for(int u=0; u<number; u++){
                    result.append("*");
                }
            }else if(c=='?'){
                result.append("*");
            }else{
                result.append(c);
            }
        }

        return result.toString();
    }


}