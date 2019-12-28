package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 10/29/2019 11:58 PM
 * This class demonstrates how to reverse a singly linked list both iteratively and recursively
 */

//Reference : https://www.geeksforgeeks.org/recursively-reversing-a-linked-list-a-simple-implementation/
//Reference : https://www.geeksforgeeks.org/reverse-a-linked-list/
public class ReverseSinglyLinkedList {

    private Node head;
    private int size;

    public ReverseSinglyLinkedList() {
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

        ReverseSinglyLinkedList list = new ReverseSinglyLinkedList();

        list.insertNodeIntoSinglyLinkedListAtBeginning(5);
        list.insertNodeIntoSinglyLinkedListAtBeginning(4);
        list.insertNodeIntoSinglyLinkedListAtBeginning(3);
        list.insertNodeIntoSinglyLinkedListAtBeginning(2);
        list.insertNodeIntoSinglyLinkedListAtBeginning(1);

        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nHead Node : " + list.getHead().getData());

        Node reversedListHeadNode = list.reverseSinglyLinkedList(list.getHead());
        list.setHead(reversedListHeadNode);

        System.out.println("\n\nAfter reversing the singly linked list");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nHead Node : " + list.getHead().getData());
    }

    public Node reverseSinglyLinkedList(Node headReference) {
        Node nextNode = null;
        Node previousNode = null;
        Node currentNode = headReference;
        while (currentNode != null) {
            nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }

    public Node reverseSinglyLinedListRecursively(Node head) {
        if (head.getNext() == null)
            return head;
        Node newHead = reverseSinglyLinedListRecursively(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
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

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            Node traversingNode = headReference;
            while (traversingNode != null) {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getNext();
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
