package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 2/14/2020 12:47 PM
 * This class demonstrates how to swap k-th node from beginning with k-th node from end in a singly linked list
 */
//Reference : https://www.geeksforgeeks.org/swap-kth-node-from-beginning-with-kth-node-from-end-in-a-linked-list/
public class SwapKthNodeFromBegWithKthNodeFromEnd {

    private Node head;
    private int size;

    public SwapKthNodeFromBegWithKthNodeFromEnd() {
        this.head = null;
        this.size = 0;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static void main(String[] args) {

        SwapKthNodeFromBegWithKthNodeFromEnd list = new SwapKthNodeFromBegWithKthNodeFromEnd();

        list.insertNodeIntoSinglyLinkedListAtEnd(1);
        list.insertNodeIntoSinglyLinkedListAtEnd(2);
        list.insertNodeIntoSinglyLinkedListAtEnd(3);
        list.insertNodeIntoSinglyLinkedListAtEnd(4);
        list.insertNodeIntoSinglyLinkedListAtEnd(5);
        list.insertNodeIntoSinglyLinkedListAtEnd(6);
        list.insertNodeIntoSinglyLinkedListAtEnd(7);
        list.insertNodeIntoSinglyLinkedListAtEnd(8);
        list.insertNodeIntoSinglyLinkedListAtEnd(9);
        list.insertNodeIntoSinglyLinkedListAtEnd(10);
        list.insertNodeIntoSinglyLinkedListAtEnd(11);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        int k = 3;
        list.replaceKthNodeFromBeginningWIthKthNodeFromEnd(list.getHead(), k);

        System.out.println("\n\nAfter swapping nodes from k-th index");
        list.displayContentsOfSinglyLinkedList(list.getHead());
    }

    public void replaceKthNodeFromBeginningWIthKthNodeFromEnd(Node headReference, int k) {
        int nodeCount = countNodes(headReference);

        if (nodeCount < k)
            return;

        //If k-th node from start and end is same, say for example : 6th node when linked list has 11 nodes
        if (2 * k - 1 == nodeCount)
            return;

        Node firstCurrent = headReference;
        Node firstCurrentPrev = null;
        for (int i = 1; i < k; i++) {
            firstCurrentPrev = firstCurrent;
            firstCurrent = firstCurrent.getNext();
        }

        Node secondCurrent = headReference;
        Node secondCurrentPev = null;
        for (int i = 1; i < nodeCount - k + 1; i++) {
            secondCurrentPev = secondCurrent;
            secondCurrent = secondCurrent.getNext();
        }

        if (firstCurrentPrev != null)
            firstCurrentPrev.setNext(secondCurrent);

        if (secondCurrentPev != null)
            secondCurrentPev.setNext(firstCurrent);


        Node temp = firstCurrent.getNext();
        firstCurrent.setNext(secondCurrent.getNext());
        secondCurrent.setNext(temp);

        //If we are replacing first or last node in singly linked list, we need to update the head reference
        if (k == 1)
            this.setHead(secondCurrent);

        if (k == nodeCount)
            this.setHead(firstCurrent);
    }

    private int countNodes(Node headReference) {
        int count = 0;
        while (headReference != null) {
            count++;
            headReference = headReference.getNext();
        }
        return count;
    }

    public void insertNodeIntoSinglyLinkedListAtEnd(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversalNode = head;
            while (traversalNode.getNext() != null) {
                traversalNode = traversalNode.getNext();
            }
            traversalNode.setNext(newNode);
        }
        size++;
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
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
