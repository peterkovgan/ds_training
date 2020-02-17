package com.company.arythmetic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeNumbers {


    public static void main(String[] args) {
         System.out.println(isPrime(223));
    }

    private static boolean isPrime(int n) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        primes.add(3);
        Set<Integer> definiteNotPrimes = new HashSet<>();
        definiteNotPrimes.add(0);
        definiteNotPrimes.add(2);
        definiteNotPrimes.add(4);
        definiteNotPrimes.add(5);
        definiteNotPrimes.add(6);
        definiteNotPrimes.add(8);

        if(primes.contains(n)) return true;
        if(definiteNotPrimes.contains(n)) return false;
        if(definiteNotPrimes.contains( n % 10)) return false;

        for(int k=4;  k <= Math.sqrt((double)n); k++){
            boolean isPrime = true;
            if(definiteNotPrimes.contains(k % 10)){
                isPrime = false;
            }else {
                for (Integer d : primes) {
                    if (d <= Math.sqrt((double) k)) {
                        if (k % d == 0) {
                            isPrime = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if(isPrime)
                primes.add(k);
        }
        boolean isPrime = true;
        for(Integer d: primes){
            if(d <= Math.sqrt((double)n)){
                if(n % d == 0){
                    isPrime   = false;
                    break;
                }
            }else{
                break;
            }
        }
        return isPrime;

    }

}
