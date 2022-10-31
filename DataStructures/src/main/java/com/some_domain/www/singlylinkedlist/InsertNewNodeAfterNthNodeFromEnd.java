package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 31-10-2022 01:09 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/insert-node-n-th-node-end/
 */
public class InsertNewNodeAfterNthNodeFromEnd {

    private Node head;

    public static void main(String[] args) {

        InsertNewNodeAfterNthNodeFromEnd list = new InsertNewNodeAfterNthNodeFromEnd();
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

        int n = 4;
        int newNode = 100;
        System.out.println("\nAfter inserting new node after " + n + "th node from end");
        list.insertNodeAfterNthNodeFromEndUsingListLength(list.getHead(), newNode, n);
        list.displayContentsOfSLL(list.getHead());
        System.out.println("\nTime complexity is O(N) but list is traversed twice");

        newNode = 200;
        System.out.println("\nAfter inserting new node after " + n + "th node from end");
        list.insertNodeAfterNthNodeFromEndUsingTwoPointers(list.getHead(), newNode, n);
        list.displayContentsOfSLL(list.getHead());
        System.out.println("\nTime complexity is O(N) list is traversed only once");

    }

    private void insertNodeAfterNthNodeFromEndUsingTwoPointers(Node headReference, int data, int n) {
        if (headReference == null) {
            System.out.println("SLL is empty");
        } else {
            Node newNode = new Node(data, null);
            Node slowPointer = headReference;
            Node fastPointer = headReference;

            for (int i = 0; i < n; i++) {
                fastPointer = fastPointer.getNext();
            }

            while (fastPointer != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext();
            }

            newNode.setNext(slowPointer.getNext());
            slowPointer.setNext(newNode);
        }
    }

    /*
        Using node count, number of nodes in the list, list length - two traversals
     */
    private void insertNodeAfterNthNodeFromEndUsingListLength(Node headReference, int data, int n) {
        int nodeCount = getNodeCount(headReference);
        Node newNode = new Node(data, null);

        int nodesToSkip = nodeCount - n + 1;

        Node currentNode = headReference;
        Node previousNode = null;
        for (int i = 0; i < nodesToSkip; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        previousNode.setNext(newNode);
        newNode.setNext(currentNode);
    }

    private int getNodeCount(Node headReference) {
        if (headReference == null)
            return 0;
        else
            return 1 + getNodeCount(headReference.getNext());
    }

    private void insertNodeAtEnd(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null)
                currentNode = currentNode.getNext();
            currentNode.setNext(newNode);
        }
    }

    private void displayContentsOfSLL(Node headReference) {
        if (headReference == null)
            System.out.println("SLL is empty");
        else {
            Node traversalNode = headReference;
            while (traversalNode != null) {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getNext();
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
