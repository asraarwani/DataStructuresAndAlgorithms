package com.some_domain.www.array;

/**
 * @author : waniasra
 * @date : 12/9/2019 8:37 PM
 */
//Reference : https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
//Reference  : https://www.interviewbit.com/problems/max-distance/
public class MaxDistanceInArray {

    public static void main(String[] args) {

        MaxDistanceInArray instance = new MaxDistanceInArray();
        int arr[] = {3, 5, 4, 2};
        int n = arr.length;
        int maxDiff = instance.findIndexDifference(arr, n);
        System.out.println("Maximum of j - i subjected to the constraint of A[i] <= A[j] is : " + maxDiff);
        System.out.println("Time and space complexity is O(N)");
    }

    /*
        Method 3
     */
    public int findIndexDifference(int[] array, int n) {
        int indexDifference = -1;
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];


        //Construct leftMin[] such that leftMin[i] stores values from array[0] , array[1 ] .. array[i]
        leftMin[0] = array[0];
        for (int i = 1; i < n; ++i) {
            leftMin[i] = Math.min(array[i], leftMin[i - 1]);
        }

        //Construct rightMax[] such that rightMax[j] stores values from array[j] , array[j+1], ... array[n-1]
        rightMax[n - 1] = array[n - 1];
        for (int j = n - 2; j >= 0; --j) {
            rightMax[j] = Math.max(array[j], rightMax[j + 1]);
        }

        //Traverse both the arrays from left to right to find optimum j - i
        int i = 0;
        int j = 0;
        while (j < n && i < n) {
            if (leftMin[i] < rightMax[j]) {
                indexDifference = Math.max(indexDifference, j - i);
                j = j + 1;
            } else {
                i = i + 1;
            }
        }
        return indexDifference;
    }
}
