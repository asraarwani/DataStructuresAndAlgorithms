package com.some_domain.www.dynamicprogramming;

/**
 * @author : waniasra
 * @date : 2/6/2020 11:13 AM
 * This class demonstrates how to print the nth fibonacci number
 */
//Reference : https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
public class FibonacciNumber {

    public static void main(String[] args) {
        FibonacciNumber instance = new FibonacciNumber();
        int n = 40;
        Long startTime = System.currentTimeMillis();
        int fibonacciNumber = instance.getNthFibonacciNumber(n);
        System.out.println("Time taken using ordinary approach : " + (System.currentTimeMillis() - startTime) + " ms. Time complexity is O(2^n)");
        System.out.println(fibonacciNumber);

        int[] memo = new int[n + 1];
        startTime = System.currentTimeMillis();
        fibonacciNumber = instance.getNthFibonacciNumber_DP_TopDown(n, memo);
        System.out.println("Time taken using top down approach : " + (System.currentTimeMillis() - startTime) + " ms. Time complexity is O(N)");
        System.out.println(fibonacciNumber);

        startTime = System.currentTimeMillis();
        fibonacciNumber = instance.getNthFibonacciNumber_DP_BottomUp(n);
        System.out.println("Time taken using bottom up approach : " + (System.currentTimeMillis() - startTime) + " ms. Time complexity is O(N)");
        System.out.println(fibonacciNumber);

        System.out.println();
        instance.printSeries(n);
    }

    public int getNthFibonacciNumber_DP_BottomUp(int n) {
        if (n == 1 || n == 2)
            return 1;
        else {
            int[] dpTable = new int[n + 2];
            dpTable[0] = 0;
            dpTable[1] = 1;
            for (int i = 2; i <= n; i++) {
                dpTable[i] = dpTable[i - 1] + dpTable[i - 2];
            }
            return dpTable[n];
        }
    }

    public int getNthFibonacciNumber_DP_TopDown(int n, int[] dpTable) {
        if (dpTable[n] != 0) {
            return dpTable[n];
        }
        if (n == 1 || n == 2)
            return 1;
        else {
            dpTable[n] = getNthFibonacciNumber_DP_TopDown(n - 1, dpTable) + getNthFibonacciNumber_DP_TopDown(n - 2, dpTable);
            return dpTable[n];
        }
    }

    public int getNthFibonacciNumber(int n) {
        if (n == 1 || n == 2)
            return 1;
        else
            return getNthFibonacciNumber(n - 1) + getNthFibonacciNumber(n - 2);
    }

    public void printSeries(int n) {
        for (int i = 1; i < n; i++) {
            System.out.print(getNthFibonacciNumber_DP_BottomUp(i) + " ");
        }
    }
}

