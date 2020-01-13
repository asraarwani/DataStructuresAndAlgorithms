package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/2/2020 10:32 PM
 * This class demonstrates how to swap pairs of nodes in singly linked list without swapping the data
 */
//Reference : https://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list-by-changing-links/
public class PairwiseSwap {

    private Node head;
    private int size;

    public PairwiseSwap() {
        this.head = null;
        this.size = 0;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public static void main(String[] args) {

        PairwiseSwap singlyLinkedList = new PairwiseSwap();
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(10);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(20);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(30);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(40);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(50);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(60);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(70);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(80);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(90);
        singlyLinkedList.insertNodeAtEndOfSinglyLinkedList(100);

        singlyLinkedList.displayContentsOfSinglyLinkedList(singlyLinkedList.getHead());

        Node newHead = singlyLinkedList.pairwiseSwapWithoutSwappingDataIteratively(singlyLinkedList.getHead());
        singlyLinkedList.setHead(newHead);

        System.out.println();
        singlyLinkedList.displayContentsOfSinglyLinkedList(singlyLinkedList.getHead());

        System.out.println("\nSwapping nodes back again using recursive method");
        newHead = singlyLinkedList.pairwiseSwapWithoutSwappingDataRecursively(singlyLinkedList.getHead());
        singlyLinkedList.setHead(newHead);
        singlyLinkedList.displayContentsOfSinglyLinkedList(singlyLinkedList.getHead());

        System.out.println("\nTime complexity is O(N) where N is the number of nodes in singly linked list");
    }

    public Node pairwiseSwapWithoutSwappingDataIteratively(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return null;
        } else {
            Node currentNode = headReference;
            Node newHead = currentNode.getNext();
            Node nextNextNode = null;
            Node nextNode = null;
            while (true) {
                nextNode = currentNode.getNext();
                nextNextNode = nextNode.getNext();
                nextNode.setNext(currentNode);

                //Terminating condition
                if (nextNextNode == null || nextNextNode.getNext() == null) {
                    currentNode.setNext(nextNextNode); // In case we have odd number of nodes in singly linked list
                    // currentNode.setNext(null);         //In case we have even number of nodes in singly linked list
                    break;
                }

                currentNode.setNext(nextNextNode.getNext());
                currentNode = nextNextNode;
            }
            return newHead;
        }
    }

    public Node pairwiseSwapWithoutSwappingDataRecursively(Node headReference) {

        if (headReference == null || headReference.getNext() == null)
            return headReference;

        Node remaining = headReference.getNext().getNext();
        Node newHead = headReference.getNext();

        headReference.getNext().setNext(headReference);

        headReference.setNext(pairwiseSwapWithoutSwappingDataRecursively(remaining));

        return newHead;
    }

    public void insertNodeAtEndOfSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversingNode = head;
            while (traversingNode.getNext() != null) {
                traversingNode = traversingNode.getNext();
            }
            traversingNode.setNext(newNode);
        }
        size++;
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null)
            System.out.println("Singly linked list is empty");
        else {
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
