package com.some_domain.www.interviewquestions;

public class MiddleIndexWhereSumMatches {

    public static void main(String[] args) {

        MiddleIndexWhereSumMatches instance = new MiddleIndexWhereSumMatches();
        int[] array = {2, 5, 6, 8, 5, 15, 1};
        //int[] array = {1, 3, 5, 2, 2};
        instance.getMiddleIndexWhereSumMatches(array);

        int equilibriumPoint = instance.equilibriumPoint(array);
        System.out.println("Equilibrium point : " + equilibriumPoint);
    }

    private void getMiddleIndexWhereSumMatches(int[] array) {
        int leftSum = 0;
        int rightSum = 0;
        int start = 0;
        int end = array.length - 1;
        if (array.length == 1) {
            System.out.println("Index where sum matches is : " + 0);
        }
        while (true) {
            if (leftSum <= rightSum) {
                leftSum += array[start];
                start++;
            } else {
                rightSum += array[end];
                end--;
            }
            if (start >= end) {
                if (leftSum == rightSum) {
                    System.out.println("Index where sum matches is : " + (start + 1));
                    break;
                } else {
                    System.out.println("Couldn't find the index where left and right sum matches");
                    break;
                }
            }
        }
    }

    public int equilibriumPoint(int array[]) {
        int leftSum = 0;
        int rightSum = 0;
        int start = 0;
        int end = array.length - 1;
        if (array.length == 1) {
            return 1;
        }
        while (true) {
            if (leftSum <= rightSum) {
                leftSum += array[start];
                start++;
            } else {
                rightSum += array[end];
                end--;
            }
            if (start >= end) {
                if (leftSum == rightSum) {
                    return start + 1;
                } else {
                    return -1;
                }
            }
        }
    }
}
