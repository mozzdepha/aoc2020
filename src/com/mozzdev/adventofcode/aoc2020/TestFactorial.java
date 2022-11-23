package com.mozzdev.adventofcode.aoc2020;

import java.text.DecimalFormat;

public class TestFactorial {

    public static void main(String[] args) {
        for (int x=1; x<15; x++) {
            double result = Math.pow(x, x-1) -1;
            System.out.println("" + x + ": " + DecimalFormat.getInstance().format(result));
        }
    }

    //        long n = removabales, r = removabales-1, comb, per;
//        per = factorial(n) / factorial(n-r);
//        System.out.println("Permutation: " + per);
//        comb = factorial(n) / (factorial(r) * factorial(n-r));
//        System.out.println("Combination: " + comb);
//
//        System.out.println(removabales);
//        System.out.println(Math.pow(removabales, (removabales-1))-1);
    static long factorial(long n) {
        long fact = 1;
        int i = 1;
        while(i <= n) {
            fact *= i;
            i++;
        }
        return fact;
    }
}
