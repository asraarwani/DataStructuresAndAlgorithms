package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 30-09-2022 08:59 pm
 */
public class PrimeNumber {

    public static void main(String[] args) {

        int number = 7;
        PrimeNumber instance = new PrimeNumber();
        boolean isPrime = instance.isPrime(number);
        if (isPrime) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1)
            return false;
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return isPrime;
    }
}
