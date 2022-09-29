package com.some_domain.www.singlylinkedlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : asraar
 * @date : 29-09-2022 02:51 pm
 * <p>
 * Reference : https://www.youtube.com/watch?v=zLcNwcR6yO4&ab_channel=KevinNaughtonJr.
 * https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
 */
public class MergeKSortedSinglyLinkedLists {

    private Node head;

    public static void main(String[] args) {

        MergeKSortedSinglyLinkedLists firstList = new MergeKSortedSinglyLinkedLists();
        firstList.insertNodeInOrder(5);
        firstList.insertNodeInOrder(1);
        firstList.insertNodeInOrder(4);
        firstList.insertNodeInOrder(3);
        firstList.insertNodeInOrder(2);
        firstList.displayContents(firstList.getHead());

        System.out.println();
        MergeKSortedSinglyLinkedLists secondList = new MergeKSortedSinglyLinkedLists();
        secondList.insertNodeInOrder(7);
        secondList.insertNodeInOrder(6);
        secondList.insertNodeInOrder(10);
        secondList.insertNodeInOrder(9);
        secondList.insertNodeInOrder(8);
        secondList.displayContents(secondList.getHead());

        System.out.println();
        MergeKSortedSinglyLinkedLists thirdList = new MergeKSortedSinglyLinkedLists();
        thirdList.insertNodeInOrder(15);
        thirdList.insertNodeInOrder(12);
        thirdList.insertNodeInOrder(14);
        thirdList.insertNodeInOrder(11);
        thirdList.insertNodeInOrder(13);
        thirdList.displayContents(thirdList.getHead());

        System.out.println();
        List<Node> lists = new ArrayList<>();
        lists.add(firstList.getHead());
        lists.add(secondList.getHead());
        lists.add(thirdList.getHead());
        MergeKSortedSinglyLinkedLists mergedList = new MergeKSortedSinglyLinkedLists();
        Node newHead = mergedList.mergeKSortedSinglyLinkedLists(lists);
        mergedList.setHead(newHead);
        mergedList.displayContents(mergedList.getHead());
        System.out.println("\nTime Complexity: O(NlogN), Auxiliary Space: O(N*K)");

        System.out.println("\nEnhanced approach");
        newHead = mergedList.mergeKSortedSinglyLinkedListsOptimized(lists);
        mergedList.setHead(newHead);
        mergedList.displayContents(mergedList.getHead());
        System.out.println("\nTime Complexity: O(NlogK), Auxiliary Space: O(K)");

    }


    private Node mergeKSortedSinglyLinkedListsOptimized(List<Node> lists) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComparator());
        //Add the first node of each list to the min-heap
        for (Node head : lists) {
            queue.add(head);
        }
        Node mergedListHead = new Node(Integer.MIN_VALUE, null);
        Node mergedListHeadCopy = mergedListHead;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            mergedListHead.setNext(currentNode);
            mergedListHead = mergedListHead.getNext();

            if (currentNode.getNext() != null) {
                queue.add(currentNode.getNext());
            }
        }
        return mergedListHeadCopy.getNext();
    }

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node firstNode, Node secondNode) {
            if (firstNode.getData() > secondNode.getData())
                return 1;
            else if (firstNode.getData() < secondNode.getData())
                return -1;
            else
                return 0;
        }
    }

    private Node mergeKSortedSinglyLinkedLists(List<Node> lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Node head : lists) {
            while (head != null) {
                minHeap.add((head.getData()));
                head = head.getNext();
            }
        }
        Node mergedListHead = new Node(Integer.MIN_VALUE, null);
        Node mergedListHeadCopy = mergedListHead;
        while (!minHeap.isEmpty()) {
            mergedListHead.setNext(new Node(minHeap.remove(), null));
            mergedListHead = mergedListHead.getNext();
        }
        return mergedListHeadCopy.getNext();
    }

    public void insertNodeInOrder(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else if (newNode.getData() < head.getData()) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node firstNode = head;
            Node secondNode = head.getNext();
            boolean nodeInserted = false;
            while (firstNode != null && secondNode != null && !nodeInserted) {
                if (newNode.getData() > firstNode.getData() && newNode.getData() <= secondNode.getData()) {
                    firstNode.setNext(newNode);
                    newNode.setNext(secondNode);
                    nodeInserted = true;
                }
                firstNode = firstNode.getNext();
                secondNode = secondNode.getNext();
            }
            if (!nodeInserted) {
                firstNode.setNext(newNode);
            }
        }
    }

    public void displayContents(Node headReference) {
        if (headReference == null)
            System.out.println("Sorted singly linked list is empty");
        else {
            do {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            } while (headReference != null);
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    private class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
