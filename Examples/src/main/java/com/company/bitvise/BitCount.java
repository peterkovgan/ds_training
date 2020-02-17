package com.company.bitvise;

public class BitCount {

    public static void main(String[] args) {
        int res = hammingWeight(22);
        System.out.println("res="+res);
    }

    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            //N WILL BE SHIFTED to right by i
            System.out.println(n+ " (" + Integer.toBinaryString(n) + ") shifted by  " + i );
            int shiftedN=n >>> i;
            System.out.println("shiftedN " + Integer.toBinaryString(shiftedN) );

            int shiftedNAndOne = shiftedN  &  1;
            System.out.println("shiftedNAndOne " + Integer.toBinaryString(shiftedNAndOne) );

            if (shiftedNAndOne == 1) {
                ++count;
                System.out.println("Added");
            }else{
                System.out.println("No");
            }
        }
        return count;
    }

}
