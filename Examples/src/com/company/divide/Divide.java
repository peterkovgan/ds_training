package com.company.divide;

public class Divide {

    /**
     *
     * https://leetcode.com/problems/divide-two-integers/
     *
     * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
     *
     * Return the quotient after dividing dividend by divisor.
     *
     * The integer division should truncate toward zero.
     *
     * Example 1:
     *
     * Input: dividend = 10, divisor = 3
     * Output: 3
     * Example 2:
     *
     * Input: dividend = 7, divisor = -3
     * Output: -2
     * Note:
     *
     * Both dividend and divisor will be 32-bit signed integers.
     * The divisor will never be 0.
     *
     * Assume we are dealing with an environment which could only store integers within the
     *
     * 32-bit signed integer range: [−2^31 ...  2^31 − 1].
     * For the purpose of this problem, assume that your function returns -2^31  when the division result overflows.
     */

    public static void main(String[] args) {
        System.out.println(new Divide().divide(1554964539,-852329655 ));
    }

    public int divide(int dividend, int divisor) {

        if(dividend==Integer.MIN_VALUE && divisor==1) return Integer.MIN_VALUE;
        if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
        if(dividend==Integer.MIN_VALUE && divisor==Integer.MIN_VALUE) return 1;
        if(divisor==Integer.MIN_VALUE) return 0;//??
        if(dividend > 0 && divisor >0  && (dividend < divisor)) return 0;//very important

        int sign = 1;  //positive

        if(dividend < 0 && divisor>0){           //negative dividend
            dividend = -dividend;                //make it positive
            sign=-1;                             //note sign change
        }

        if(divisor < 0 && dividend >= 0){        //negative divisor
            divisor = -divisor;                  //make it positive
            sign=-1;                             //note sign change
        }

        if(divisor < 0 && dividend < 0){         //both negative
            dividend = -dividend;                //make both positive
            divisor  = -divisor;                 //and not note any sign change(it will not be required)
        }


        int q = divisor;                                          //3

        int steps = 0;

        int tempDivisor = divisor;                                //3

        int tenFactorDevisor[] = new int[10];

        int tenFactorDevisorNum[] = new int[10];

        int index = 0;

        int stepIncrease=1;

        int f = 1;


        //if divisor=3, this will be 3,30,300...
        tenFactorDevisor[index]    = tempDivisor;          //tenFactorDevisor[0]    = 3;
        //this is always:            1,10,100...
        tenFactorDevisorNum[index] = stepIncrease;         //tenFactorDevisorNum[0]  = 1;   in index [1] value would be assumed 30


        //progressively try to increase divisor by itself   (divisor+divisor)
        //if such number grows > dividend on time T(T<10) - so we stay in this index and go (in the bottom) to subtract this divisor from dividend T times
        //if such number grows more than 10 times and still small, we increase index and tenFactorDevisor[index] becomes divisor*10   and tenFactorDevisorNum[index] will grow

        while(true){

            if(Integer.MAX_VALUE - tempDivisor < q){   //Integer.MAX_VALUE - 3 > 3
                                                       //Integer.MAX_VALUE - 6 > 3
                break;
            }

            tempDivisor = tempDivisor + q;             //tempDivisor=3+3=6
                                                       //tempDivisor=6+3=9

            if(tempDivisor > dividend){                // 6 == 6 , so no break
                                                       // 9 > 6, so break
                break;
            }

            steps++;                                   //steps=1

            stepIncrease = stepIncrease + f;           //stepIncrease=1+1 = 2


            if(steps == 9){                            //no

                steps = 0;

                f = stepIncrease;

                q = tempDivisor;

                index = index+1;

                tenFactorDevisor[index] = tempDivisor;
                tenFactorDevisorNum[index] = stepIncrease;
            }
        }

        //System.out.println(index);

        //index now represents the max factor
        //divisor 6
        //0  1  2   3    4     5
        //6  60 600 6000 60000 600000

        int counter = 0;

        while(true){

            if(dividend - tenFactorDevisor[index] >= 0){                  //6-3 >=0                              3-3==0                         ups, go to else
                dividend = dividend - tenFactorDevisor[index];            //dividend = 6 - 3 = 3                 dividend = 3 - 3 = 0
                counter += tenFactorDevisorNum[index];                    //counter = 0+1=1                      counter = 1+1=2
            }else{
                index--;
            }
            if(index==-1){
                break;
            }
        }



        return counter * sign;    //2

    }
}
