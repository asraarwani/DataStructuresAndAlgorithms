package com.some_domain.www.interviewquestions;

public class PrimeNumberSeries {

    public static void main(String[] args) {

        PrimeNumberSeries instance = new PrimeNumberSeries();
        int limit = 100;
        instance.generatePrimeNumberSeries(limit);
    }

    private void generatePrimeNumberSeries(int limit) {
        for (int i = 2; i <= limit; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
            }
        }
    }
}
