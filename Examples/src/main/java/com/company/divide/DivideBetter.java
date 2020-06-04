package com.company.divide;

public class DivideBetter {

    public static void main(String[] args) {
        System.out.println(new Divide().divide(1554964539,-852329655 ));
    }

    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if(divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        boolean overflow = false;
        int signed = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? 1 : -1;
        if(dividend == Integer.MIN_VALUE) {
            dividend++;
            overflow = true;
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int res = divisor;

        if(dividend == divisor) return signed;

        if(dividend < divisor) return 0;


        int quotient = 0, quo = 1;

        while(dividend >= divisor) { //see pic

            while(res <= dividend - res) { //while (dividend > res*2)
                res = res << 1; //res=res*2
                quo = quo << 1; //quo=quo*2
            }

            quotient += quo;
            dividend -= res;
            if(overflow) {
                dividend++;//now, when big number reduced (by dividend -= res) and it was overflown minus in past, we can bring it back to original value
                overflow = false;
            }
            res = divisor;//restart multiplications
            quo = 1;
        }
        return signed*quotient;
    }
}
