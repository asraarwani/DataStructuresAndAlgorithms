package com.some_domain.www.interviewquestions;

public class MiddleIndexWhereSumMatches {

    public static void main(String[] args) {

        MiddleIndexWhereSumMatches instance = new MiddleIndexWhereSumMatches();
        int[] array = {2, 5, 6, 8, 5, 15, 1};
        instance.getMiddleIndexWhereSumMatches(array);
    }

    private void getMiddleIndexWhereSumMatches(int[] array) {
        int leftSum = 0;
        int rightSum = 0;
        int start = 0;
        int end = array.length - 1;
        while (true) {
            if (leftSum < rightSum) {
                leftSum += array[start];
                start++;
            } else {
                rightSum += array[end];
                end--;
            }
            if (start > end) {
                if (leftSum == rightSum) {
                    System.out.println("Index where sum matches is : " +(start - 1));
                    break;
                } else {
                    System.out.println("Couldn't find the index where left and right sum matches");
                    break;
                }
            }
        }
    }
}
