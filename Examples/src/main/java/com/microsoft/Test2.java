package com.microsoft;

public class Test2 {

    public static void main(String[] args) {

        int [] param = new int[]{1,2,3};

        new Test2().doIt(param);

        System.out.println(new Test2().doIt2(param));

    }

    void doIt(int [] param){

    }

    int doIt2(int [] param){
        return 0;
    }

}
