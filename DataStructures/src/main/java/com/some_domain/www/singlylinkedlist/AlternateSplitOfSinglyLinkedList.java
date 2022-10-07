package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 07-10-2022 03:42 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/alternating-split-of-a-given-singly-linked-list/
 */
public class AlternateSplitOfSinglyLinkedList {

    private Node head;


    public static void main(String[] args) {

        AlternateSplitOfSinglyLinkedList list = new AlternateSplitOfSinglyLinkedList();
        list.insertNodeAtEnd(1);
        list.insertNodeAtEnd(2);
        list.insertNodeAtEnd(3);
        list.insertNodeAtEnd(4);
        list.insertNodeAtEnd(5);
        list.insertNodeAtEnd(6);
        list.insertNodeAtEnd(7);
        list.insertNodeAtEnd(8);
        list.insertNodeAtEnd(9);
        list.insertNodeAtEnd(10);
        list.displayContentsOfSLL(list.getHead());

        AlternateSplitOfSinglyLinkedList firstList = new AlternateSplitOfSinglyLinkedList();
        AlternateSplitOfSinglyLinkedList secondList = new AlternateSplitOfSinglyLinkedList();

        list.alternateSplitSinglyLinkedListUsingDummyNodes(list.getHead(), firstList, secondList);

        System.out.println();
        System.out.println("First sub-list");
        firstList.displayContentsOfSLL(firstList.getHead());

        System.out.println();
        System.out.println("Second sub-list");
        secondList.displayContentsOfSLL(secondList.getHead());

        System.out.println("\nTime complexity is O(N)");

    }

    private void alternateSplitSinglyLinkedListUsingDummyNodes(Node headReference, AlternateSplitOfSinglyLinkedList firstList, AlternateSplitOfSinglyLinkedList secondList) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            Node firstListHead = new Node(Integer.MIN_VALUE, null);
            Node firstListHeadRef = firstListHead;
            Node secondListHead = new Node(Integer.MIN_VALUE, null);
            Node secondListHeadRef = secondListHead;
            Node currentNode = headReference;
            Node nextNode = null;
            while (currentNode != null && currentNode.getNext() != null) {
                nextNode = currentNode.getNext();

                firstListHead.setNext(currentNode);
                currentNode.setNext(null);

                secondListHead.setNext(nextNode);

                firstListHead = firstListHead.getNext();
                secondListHead = secondListHead.getNext();
                currentNode = nextNode.getNext();
            }
            firstList.setHead(firstListHeadRef.getNext());
            secondList.setHead(secondListHeadRef.getNext());
        }
    }

    private void insertNodeAtEnd(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversalNode = head;
            while (traversalNode.getNext() != null)
                traversalNode = traversalNode.getNext();
            traversalNode.setNext(newNode);
        }
    }

    public void displayContentsOfSLL(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
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
