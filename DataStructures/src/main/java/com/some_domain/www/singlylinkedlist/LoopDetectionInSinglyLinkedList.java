package com.some_domain.www.singlylinkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : waniasra
 * @date : 2/15/2020 10:40 AM
 * This class demonstrates how to detect a loop in singly linked list
 */
//Reference : https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
public class LoopDetectionInSinglyLinkedList {

    private Node head;
    private int size;

    public LoopDetectionInSinglyLinkedList() {
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

        LoopDetectionInSinglyLinkedList list = new LoopDetectionInSinglyLinkedList();

        list.insertNodeIntoSinglyLinkedListAtBeginning(10);
        list.insertNodeIntoSinglyLinkedListAtBeginning(9);
        list.insertNodeIntoSinglyLinkedListAtBeginning(8);
        list.insertNodeIntoSinglyLinkedListAtBeginning(7);
        list.insertNodeIntoSinglyLinkedListAtBeginning(6);
        list.insertNodeIntoSinglyLinkedListAtBeginning(5);
        list.insertNodeIntoSinglyLinkedListAtBeginning(4);
        list.insertNodeIntoSinglyLinkedListAtBeginning(3);
        list.insertNodeIntoSinglyLinkedListAtBeginning(2);
        list.insertNodeIntoSinglyLinkedListAtBeginning(1);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        //Creating  a loop in singly linked list
        list.createLoopInSinglyLinkedList(list.getHead());

        System.out.println();
        boolean containsLoop = list.detectLoopUsingHashing(list.getHead());
        System.out.println("Contains loop : " + containsLoop + ". Time and space complexity is O(N)");

        containsLoop = list.containsLoopUsingFloydsAlgorithm(list.getHead());
        System.out.println("Contains loop : " + containsLoop + ". Time and space complexity is O(N) and O(1) resp.");
    }

    public boolean containsLoopUsingFloydsAlgorithm(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return false;
        } else {
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            while (fastPointer != null && fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
                if (slowPointer == fastPointer) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean detectLoopUsingHashing(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return false;
        } else {
            Set<Node> set = new HashSet<>();
            while (headReference != null) {

                if (set.contains(headReference))
                    return true;

                set.add(headReference);
                headReference = headReference.getNext();
            }
            return false;
        }
    }

    private void createLoopInSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return;
        } else {
            while (headReference.getNext() != null)
                headReference = headReference.getNext();
            headReference.setNext(head);
        }
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return;
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void insertNodeIntoSinglyLinkedListAtBeginning(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
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


