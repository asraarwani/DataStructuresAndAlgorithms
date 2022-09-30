package com.some_domain.www.interviewquestions;

public class PrimeNumbersInGivenRange {

    public static void main(String[] args) {

        PrimeNumbersInGivenRange instance = new PrimeNumbersInGivenRange();
        int startingRange = 2;
        int endingRange = 100;
        instance.generatePrimeNumbersInRange(startingRange, endingRange);
    }

    private void generatePrimeNumbersInRange(int startingRange, int endingRange) {
        for (int i = startingRange; i <= endingRange; i++) {
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
