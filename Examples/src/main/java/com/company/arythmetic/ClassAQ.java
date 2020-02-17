package com.company.arythmetic;

import java.util.stream.IntStream;

public class ClassAQ {

    public static void main(String [] args){
         IntStream.range(1, 16).mapToObj(e->(e%3==0 && e%5!=0)?"FUZZ":(e%5==0 && e%3!=0)?"BUZZ":(e%3==0 && e%5==0)?"FUZZBUZZ":e+"").forEach(System.out::println);
    }


}
