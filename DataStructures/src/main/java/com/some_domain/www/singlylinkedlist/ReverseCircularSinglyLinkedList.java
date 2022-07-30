package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 30-07-2022 01:21 pm
 * This class demonstrates how to reverse a circular singly linked list
 */
public class ReverseCircularSinglyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public ReverseCircularSinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        ReverseCircularSinglyLinkedList list = new ReverseCircularSinglyLinkedList();
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(10);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(9);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(8);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(7);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(6);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(5);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(4);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(3);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(2);
        list.insertNodeIntoCircularSinglyLinkedListAtBeginning(1);


        list.displayContentsOfCircularSinglyLinkedList();
        System.out.println("\nNumber of nodes is : " + list.getSize() + ", Head Node : " + list.getHead().getData() + ", Tail Node : " + list.getTail().getData());


        list.reverseCircularSinglyLinkedList();
        System.out.println("\nAfter reversing the circular singly linked list");
        list.displayContentsOfCircularSinglyLinkedList();
        System.out.println("\nNumber of nodes is : " + list.getSize() + ", Head Node :" + list.getHead().getData() + ", Tail Node : " + list.getTail().getData());

    }

    public void reverseCircularSinglyLinkedList() {
        if (head == null) {
            System.out.println("Circular singly linked list is empty.");
        } else {
            Node traversalNode = head;
            Node newTail = head;
            Node nextNode = null;
            Node previousNode = tail;
            do {
                nextNode = traversalNode.getNext();
                traversalNode.setNext(previousNode);
                previousNode = traversalNode;
                traversalNode = nextNode;
            } while (traversalNode != head);
            this.head = previousNode;
            this.tail = newTail;
            tail.setNext(head);
        }
    }

    private void insertNodeIntoCircularSinglyLinkedListAtBeginning(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = tail = newNode;
            tail.setNext(head);
        } else {
            newNode.setNext(head);
            head = newNode;
            tail.setNext(head);
        }
        size++;
    }

    private void displayContentsOfCircularSinglyLinkedList() {
        if (head == null) {
            System.out.println("Circular singly linked list is empty.");
        } else {
            Node traversalNode = head;
            do {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getNext();
            } while (traversalNode != head);
        }
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

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
