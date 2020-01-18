package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/18/2020 11:19 AM
 * This class demonstrates how to add two numbers represented by two singly linked lists
 */
//Reference :  https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
public class AddTwoNumbers {

    private Node head;
    private int size;

    public AddTwoNumbers() {
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

        AddTwoNumbers firstNumber = new AddTwoNumbers();
        firstNumber.insertNodeAtEndOfSinglyLinkedList(7);
        firstNumber.insertNodeAtEndOfSinglyLinkedList(5);
        firstNumber.insertNodeAtEndOfSinglyLinkedList(9);
        firstNumber.insertNodeAtEndOfSinglyLinkedList(4);
        firstNumber.insertNodeAtEndOfSinglyLinkedList(6);
        firstNumber.displayContentsOfSinglyLinkedList(firstNumber.getHead());

        System.out.println();
        AddTwoNumbers secondNumber = new AddTwoNumbers();
        secondNumber.insertNodeAtEndOfSinglyLinkedList(8);
        secondNumber.insertNodeAtEndOfSinglyLinkedList(4);
        secondNumber.displayContentsOfSinglyLinkedList(secondNumber.getHead());

        System.out.println();
        AddTwoNumbers resultList = new AddTwoNumbers();
        Node resultListHeadNode = resultList.addTwoNumbers(firstNumber.getHead(), secondNumber.getHead());
        resultList.setHead(resultListHeadNode);
        resultList.displayContentsOfSinglyLinkedList(resultList.getHead());
        System.out.println("\nTime complexity is O(max(N, M)) where N and M are the number of nodes in the two singly linked lists");
    }

    public Node addTwoNumbers(Node firstNumberHead, Node secondNumberHead) {
        Node resultNumberHead = null;
        int sum = 0;
        int carry = 0;
        Node previousNode = null;
        while (firstNumberHead != null || secondNumberHead != null) {
            sum = sum + carry;

            if (firstNumberHead != null) {
                sum = sum + firstNumberHead.getData();
                firstNumberHead = firstNumberHead.getNext();
            }

            if (secondNumberHead != null) {
                sum = sum + secondNumberHead.getData();
                secondNumberHead = secondNumberHead.getNext();
            }

            carry = sum >= 10 ? 1 : 0;
            Node newNode = new Node(sum % 10, null);
            if (previousNode == null) {
                previousNode = resultNumberHead = newNode;
            } else {
                previousNode.setNext(newNode);
                previousNode = previousNode.getNext();
            }
            sum = 0;
        }

        if (carry > 0) {
            previousNode.setNext(new Node(carry, null));
        }
        return resultNumberHead;
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
            return;
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void insertNodeAtEndOfSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversalNode = head;
            while (traversalNode.getNext() != null)
                traversalNode = traversalNode.getNext();
            traversalNode.setNext(newNode);
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
