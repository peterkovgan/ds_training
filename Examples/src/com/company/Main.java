package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	    //write your code here
        LinkedList<Integer> list = new LinkedList<>();;

        list.add(1);
        list.add(2);

        Integer f = list.getFirst();
        Integer l = list.getLast();

        System.out.println("first=" + f);
        System.out.println("last =" + l);

        String a = "a";
        String b = a.substring(1);
        System.out.println("Is b empty? "+b.isEmpty());
    }
}
