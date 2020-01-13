package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/13/2020 10:38 PM
 * This class demonstrates how to find the middle node in a singly linked list
 */
public class FindMiddleNodeInSinglyLinkedList {

    private Node head;
    private int size;

    public FindMiddleNodeInSinglyLinkedList() {
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

        FindMiddleNodeInSinglyLinkedList list = new FindMiddleNodeInSinglyLinkedList();
        list.insertNodeAtBeginningOfSinglyLinkedList(10);
        list.insertNodeAtBeginningOfSinglyLinkedList(9);
        list.insertNodeAtBeginningOfSinglyLinkedList(8);
        list.insertNodeAtBeginningOfSinglyLinkedList(7);
        list.insertNodeAtBeginningOfSinglyLinkedList(6);
        list.insertNodeAtBeginningOfSinglyLinkedList(5);
        list.insertNodeAtBeginningOfSinglyLinkedList(4);
        list.insertNodeAtBeginningOfSinglyLinkedList(3);
        list.insertNodeAtBeginningOfSinglyLinkedList(2);
        list.insertNodeAtBeginningOfSinglyLinkedList(1);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        Node middleNode = list.findMiddleNodeInSinglyLinkedList(list.getHead());
        if (middleNode != null) {
            System.out.println("\nMiddle node of the singly linked list is : " + middleNode.getData());
            System.out.println("Time complexity is O(N) where N is the number of nodes int he singly linked list.");
        } else {
            System.out.println("Singly linked list is empty.");
        }
    }

    public Node findMiddleNodeInSinglyLinkedList(Node headReference) {
        if (headReference == null || headReference.getNext() == null)
            return headReference;
        else {
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            while (fastPointer != null && fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
            }
            return slowPointer;
        }
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void insertNodeAtBeginningOfSinglyLinkedList(int data) {
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
