package com.microsoft;

public class Test3 {

    public static void main(String[] args) {

        int [] param = new int[]{1,2,3};

        new Test3().doIt(param);

        System.out.println(new Test3().doIt2(param));

    }

    void doIt(int [] param){

    }

    int doIt2(int [] param){
        return 0;
    }

}
