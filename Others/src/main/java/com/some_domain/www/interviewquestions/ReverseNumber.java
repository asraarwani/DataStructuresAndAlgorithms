package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 02-10-2022 12:19 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/write-a-program-to-reverse-digits-of-a-number/
 */
public class ReverseNumber {

    public static void main(String[] args) {

        ReverseNumber instance = new ReverseNumber();
        int number = 12345;
        instance.reverseGivenNumber(number);
    }

    private void reverseGivenNumber(int number) {
        int originalNumber = number;
        int remainder;
        int reverse = 0;
        while (number > 0) {
            remainder = number % 10;
            number = number / 10;
            reverse = reverse * 10 + remainder;
        }
        System.out.println("Reverse of number " + originalNumber + " is " + reverse);
    }
}
