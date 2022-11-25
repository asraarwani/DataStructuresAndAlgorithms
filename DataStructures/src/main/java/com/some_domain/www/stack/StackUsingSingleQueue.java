package com.some_domain.www.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : asraar
 * @date : 25-11-2022 02:58 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/implement-a-stack-using-single-queue/
 */
public class StackUsingSingleQueue {

    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {

        StackUsingSingleQueue instance = new StackUsingSingleQueue();
        instance.push(10);
        instance.push(20);
        instance.push(30);
        queue.stream().forEach(item -> System.out.print(item + " "));

        System.out.println();
        instance.pop();
        instance.pop();
        queue.stream().forEach(item -> System.out.print(item + " "));

    }

    public void push(int data) {
        int size = queue.size();

        queue.offer(data);

        for (int i = 0; i < size; i++) {
            int poppedItem = queue.remove();
            queue.offer(poppedItem);
        }
    }

    public void pop() {
        if (queue.size() == 0)
            System.out.println("Queue is empty");
        else {
            int poppedItem = queue.remove();
            System.out.println("Item popped is : " + poppedItem);
        }
    }
}
