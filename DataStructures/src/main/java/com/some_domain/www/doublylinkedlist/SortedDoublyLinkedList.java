package com.some_domain.www.doublylinkedlist;

import java.util.Scanner;

/**
 * @author : waniasra
 * @date : 10/9/2019 3:25 PM
 * This class demonstrates how to create a sorted doubly linked list
 */
public class SortedDoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public SortedDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        SortedDoublyLinkedList list = new SortedDoublyLinkedList();
        boolean keepRunning = true;
        int nodeData = Integer.MIN_VALUE;
        while (keepRunning) {
            System.out.println("\n");
            System.out.println("1 for inserting a node into sorted doubly linked list in order");
            System.out.println("2 for displaying contents of the sorted doubly linked list using forward traversal");
            System.out.println("3 for displaying contents of the sorted doubly linked list using backward traversal");
            System.out.println("4 for clearing the current contents of the sorted doubly linked list");
            System.out.println("0 for terminating the program");
            System.out.println("Enter your choice....");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the data of the node to be inserted");
                    nodeData = in.nextInt();
                    list.insertNodeIntoDouyblyLinkedListInOrder(nodeData);
                    break;
                case 2:
                    System.out.println("Contents of the sorted doubly linked list using forward traversal are as follows");
                    list.displayContentsUsingForwardTraversal(list.getHead());
                    break;
                case 3:
                    System.out.println("Contents of the sorted doubly linked list using backward traversal are as follows");
                    list.displayContentsUsingBackwardTraversal(list.getTail());
                    break;
                case 4:
                    list.clearCurrentContentsOfDoublyLinkedList();
                    break;
                case 0:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Input you have entered isn't valid!!!");
            }
        }
        in.close();
    }

    public void insertNodeIntoDouyblyLinkedListInOrder(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) { //If the sorted doubly linked list is empty
            head = tail = newNode;
        } else if (data < head.getData()) { //If the node to be inserted is smaller than the head node
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else if (data > tail.getData()) { //If the node to be inserted is greater than the tail node
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        } else { //If the node to be inserted is greater than head node and smaller than tail node, we need to find the appropriate position for the insertion
            Node traversalNode = head;
            Node nextNode = head.next;
            while (traversalNode != null && traversalNode.getNext() != null) {
                if (data >= traversalNode.getData() && data <= nextNode.getData()) {
                    traversalNode.setNext(newNode);
                    newNode.setPrevious(traversalNode);
                    newNode.setNext(nextNode);
                    nextNode.setPrevious(newNode);
                    break;
                }
                traversalNode = nextNode;
                nextNode = nextNode.getNext();
            }
        }
        size++;
    }

    public void displayContentsUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Sorted doubly linked list is empty.");
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void displayContentsUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Sorted doubly linked list is empty.");
        } else {
            while (tailReference != null) {
                System.out.print(tailReference.getData() + " ");
                tailReference = tailReference.getPrevious();
            }
        }
    }

    public void clearCurrentContentsOfDoublyLinkedList() {
        head = tail = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private class Node {

        private int data;
        private Node next;
        private Node previous;

        public Node(int data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }
}
