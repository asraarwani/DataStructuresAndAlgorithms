package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 01-10-2022 09:54 am
 */
public class SumOfDigitsOfNumber {

    public static void main(String[] args) {

        int number = 8975;
        SumOfDigitsOfNumber instance = new SumOfDigitsOfNumber();
        int sum = instance.getSumOfDigitsOfNumber(number);
        System.out.println("Sum of digits of number " + number + " is " + sum);

        sum = instance.getSumOfDigitsOfNumberRecursive(number);
        System.out.println("Sum of digits of number " + number + " is " + sum);
    }

    private int getSumOfDigitsOfNumber(int number) {
        int sum = 0;
        while (number > 0) {
            int remainder = number % 10;
            sum += remainder;
            number /= 10;
        }
        return sum;
    }

    private int getSumOfDigitsOfNumberRecursive(int number) {
        if (number == 0)
            return 0;
        int sum = number % 10 + getSumOfDigitsOfNumberRecursive(number / 10);
        return sum;
    }
}
