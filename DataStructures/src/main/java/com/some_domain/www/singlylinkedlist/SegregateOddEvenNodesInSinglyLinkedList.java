package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/16/2020 10:33 PM
 * This class demonstrates how to segregate even and odd nodes in singly linked list
 */
//Reference : https://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/
public class SegregateOddEvenNodesInSinglyLinkedList {

    private Node head;
    private int size;

    public SegregateOddEvenNodesInSinglyLinkedList() {
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

        SegregateOddEvenNodesInSinglyLinkedList list = new SegregateOddEvenNodesInSinglyLinkedList();

        list.insertNodeAtEndOfSinglyLinkedList(1);
        list.insertNodeAtEndOfSinglyLinkedList(2);
        list.insertNodeAtEndOfSinglyLinkedList(3);
        list.insertNodeAtEndOfSinglyLinkedList(4);
        list.insertNodeAtEndOfSinglyLinkedList(5);
        list.insertNodeAtEndOfSinglyLinkedList(6);
        list.insertNodeAtEndOfSinglyLinkedList(7);
        list.insertNodeAtEndOfSinglyLinkedList(8);
        list.insertNodeAtEndOfSinglyLinkedList(9);
        list.insertNodeAtEndOfSinglyLinkedList(10);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        Node newHead = list.segregateOddEvenNodesOfSinglyLinkedList(list.getHead());
        list.setHead(newHead);

        System.out.println("\nAfter even and odd nodes are segregated in singly linked list");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in singly linked list");
    }

    /*
        The idea is to divide the list into even and odd lists and then append the odd list to the end of the even list
     */
    public Node segregateOddEvenNodesOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return null;
        } else {
            Node oddListHead = null, oddListTail = null;
            Node evenListHead = null, evenListTail = null;
            Node currentNode = headReference;
            while (currentNode != null) {
                if (currentNode.getData() % 2 == 0) {
                    if (evenListHead == null) {
                        evenListHead = evenListTail = currentNode;
                    } else {
                        evenListTail.setNext(currentNode);
                        evenListTail = evenListTail.getNext();
                    }
                } else {
                    if (oddListHead == null) {
                        oddListHead = oddListTail = currentNode;
                    } else {
                        oddListTail.setNext(currentNode);
                        oddListTail = oddListTail.getNext();
                    }
                }
                currentNode = currentNode.getNext();
            }
            evenListTail.setNext(oddListHead);
            oddListTail.setNext(null);
            return evenListHead;
        }
    }

    public void insertNodeAtEndOfSinglyLinkedList(int data) {
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
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly lnked list is empty");
            return;
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