package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 30-09-2022 08:59 pm
 */
public class ArmstrongNumberCheck {

    public static void main(String[] args) {

        ArmstrongNumberCheck instance = new ArmstrongNumberCheck();
        int number = 153; //1634
        boolean isArmstrongNumber = instance.isArmstrongNumber(number);
        if (isArmstrongNumber) {
            System.out.println(number + " is armstrong number");
        } else {
            System.out.println(number + " is not armstrong number");
        }

        int limit = 10000;
        System.out.println("Armstrong numbers up to a given limit " + limit + " are given as follows:");
        instance.generateArmstrongNumberSeries(limit);
    }

    private boolean isArmstrongNumber(int number) {
        boolean isArmstrong = false;
        int power = findOrderOfNumber(number);
        //int power = String.valueOf(number).length();
        int sum = 0;
        int temporaryNumber = number;
        int remainder;
        while (temporaryNumber != 0) {
            remainder = temporaryNumber % 10;
            sum += Math.pow(remainder, power);
            temporaryNumber = temporaryNumber / 10;
        }
        if (number == sum)
            isArmstrong = true;
        return isArmstrong;
    }

    private int findOrderOfNumber(int number) {
        int order = 0;
        while (number != 0) {
            order++;
            number = number / 10;
        }
        return order;
    }

    private void generateArmstrongNumberSeries(int limit) {
        for (int i = 0; i < limit; i++) {
            if (isArmstrongNumber(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
