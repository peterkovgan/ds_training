package com.company;

import java.util.*;

public class ZigZag {

    public String convert(String s, int numRows) {

        if(numRows<=1) return s;
        List<Character>[] rows = new ArrayList[numRows];


        char array[] = s.toCharArray();
        boolean grow = true;
        int row = 0;
        int signChange = numRows - 1;
        int steps = 0;

        for(Character c : array){
            List<Character> list = rows[row];
            if(list == null){
                list = new ArrayList<>();
                rows[row] = list;
            }
            list.add(c);
            if(grow){
                row++;
            }else{
                row--;
            }
            steps++;
            if(steps==signChange){
                steps = 0;
                grow = !grow;
            }
        }

        int index= 0;
        for(int i = 0; i < numRows; i++){
            List<Character> list = rows[i];
            for(Character c:list){
                array[index++] = c;
            }
            if(index>array.length-1){
                break;
            }
        }

        return String.valueOf(array);

    }
}
