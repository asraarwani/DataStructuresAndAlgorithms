package com.some_domain.www.interviewquestions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : asraar
 * @date : 10-10-2022 09:46 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/reverse-an-array-in-groups-of-given-size/
 */
public class ReverseArrayInGroupsOfGivenSize {

    public static void main(String[] args) {

        ReverseArrayInGroupsOfGivenSize instance = new ReverseArrayInGroupsOfGivenSize();

        int k = 3;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        instance.reverseInGroupsUsingList(list, list.size(), k);

        System.out.println();
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        instance.reverseInGroups(array, array.length, k);
        Arrays.stream(array).forEach(item -> {
            System.out.print(item + " ");
        });

        System.out.println();
        System.out.println("Time complexity is O(N)");
    }

    void reverseInGroups(int[] array, int n, int k) {
        for (int i = 0; i < n; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1); //To handle case when k is not multiple of n
            while (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
    }


    void reverseInGroupsUsingList(ArrayList<Integer> arr, int n, int k) {
        for (int i = 0; i < n; i = i + k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1);
            while (left < right) {
                int temp = arr.get(left);
                arr.set(left, arr.get(right));
                arr.set(right, temp);
                left++;
                right--;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr.get(i) + " ");
        }
    }
}
