package com.some_domain.www.interviewquestions;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author : asraar
 * @date : 14-10-2022 09:33 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/leaders-in-an-array/
 */
public class LeadersInArray {

    public static void main(String[] args) {

        int[] array = {16, 17, 4, 3, 5, 2};
        LeadersInArray instance = new LeadersInArray();
        instance.printLeadersInArray(array);
        System.out.println("\nTime complexity is O(N)");

        instance.printLeadersInArrayUsingStackTest(array);
    }

    public ArrayList<Integer> printLeadersInArrayUsingStackTest(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int maxLeaderSoFar = Integer.MIN_VALUE;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] >= maxLeaderSoFar) {
                stack.push(array[i]);
                maxLeaderSoFar = array[i];
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public ArrayList<Integer> printLeadersInArrayUsingStack(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(array[array.length - 1]);
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] >= stack.peek()) {
                stack.push(array[i]);
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public ArrayList<Integer> printLeadersInArray(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        int leader = Integer.MIN_VALUE;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] >= leader) {
                leader = array[i];
                System.out.print(array[i] + " ");
                list.add(leader);
            }
        }
        return list;
    }
}
