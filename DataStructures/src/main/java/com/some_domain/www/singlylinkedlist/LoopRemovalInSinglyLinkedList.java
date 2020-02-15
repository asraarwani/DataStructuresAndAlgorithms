package com.some_domain.www.singlylinkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : waniasra
 * @date : 2/15/2020 10:50 AM
 */
//Reference : https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
public class LoopRemovalInSinglyLinkedList {

    private Node head;
    private int size;

    public LoopRemovalInSinglyLinkedList() {
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

        LoopRemovalInSinglyLinkedList list = new LoopRemovalInSinglyLinkedList();

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

        list.displayContentsOfSinglyLinkedList(list.getHead());

        //Create a loop in singly linked list
        list.createLoopInSinglyLinkedList(list.getHead());

        //Removing loop using hashing
        list.detectAndRemoveLoopUsingHashing(list.getHead());


        System.out.println("\nAfter loop is removed form the singly linked list using hashing");
        list.displayContentsOfSinglyLinkedList(list.getHead());

        //Create a loop in singly linked list
        list.createLoopInSinglyLinkedList(list.getHead());

        //Removing loop using Floyd's cycle finding algorithm
        list.detectAndRemoveLoopUsingFloydsAlgorithm(list.getHead());

        System.out.println("\nAfter loop is removed form the singly linked list using optimized approach (Floyd's Algorithm)");
        list.displayContentsOfSinglyLinkedList(list.getHead());

    }

    public void detectAndRemoveLoopUsingFloydsAlgorithm(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return;
        } else {
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            while (fastPointer != null && fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
                if (slowPointer == fastPointer) {
                    break;
                }
            }

            if (slowPointer == fastPointer) {
                slowPointer = headReference;
                while (fastPointer.getNext() != null && slowPointer != fastPointer.getNext()) {
                    slowPointer = slowPointer.getNext();
                    fastPointer = fastPointer.getNext();
                }
                fastPointer.setNext(null);
            }
        }
    }

    public void detectAndRemoveLoopUsingHashing(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return;
        } else {
            Set<Node> set = new HashSet<>();
            Node previousNode = null;
            while (headReference != null) {

                if (set.contains(headReference)) {
                    previousNode.setNext(null);
                    return;
                } else {
                    previousNode = headReference;
                    set.add(headReference);
                    headReference = headReference.getNext();
                }
            }
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
            do {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            } while (headReference != null);
        }
    }

    public void insertNodeIntoSinglyLinkedListAtEnd(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null)
                currentNode = currentNode.getNext();
            currentNode.setNext(newNode);
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
