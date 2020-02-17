package com.microsoft;

public class Test1 {

    public static void main(String[] args) {

        int [] param = new int[]{1,2,3};

        new Test1().doIt(param);

        System.out.println(new Test1().doIt2(param));

    }

    void doIt(int [] param){

    }

    int doIt2(int [] param){
        return 0;
    }

}
