package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 07-10-2022 04:29 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/identical-linked-lists/
 */
public class IdenticalLinkedLists {

    private Node head;

    public static void main(String[] args) {

        IdenticalLinkedLists firstList = new IdenticalLinkedLists();
        firstList.insertNodeAtEnd(1);
        firstList.insertNodeAtEnd(2);
        firstList.insertNodeAtEnd(3);
        firstList.insertNodeAtEnd(4);
        firstList.insertNodeAtEnd(5);
        firstList.displayContentsOfSLL(firstList.getHead());

        System.out.println();
        IdenticalLinkedLists secondList = new IdenticalLinkedLists();
        secondList.insertNodeAtEnd(1);
        secondList.insertNodeAtEnd(2);
        secondList.insertNodeAtEnd(3);
        secondList.insertNodeAtEnd(4);
        secondList.insertNodeAtEnd(5);
        secondList.displayContentsOfSLL(secondList.getHead());

        System.out.println();
        IdenticalLinkedLists mainList = new IdenticalLinkedLists();
        boolean areIdentical = mainList.areIdenticalListIterative(firstList.getHead(), secondList.getHead());
        if (areIdentical) {
            System.out.println("Singly linked lists are identical");
        } else {
            System.out.println("Singly linked lists are NOT identical");
        }
        System.out.println("Time complexity is O(Min(M, N)) where M and N are sizes of two lists");


        System.out.println();
        areIdentical = mainList.areIdenticalListRecursive(firstList.getHead(), secondList.getHead());
        if (areIdentical) {
            System.out.println("Singly linked lists are identical");
        } else {
            System.out.println("Singly linked lists are NOT identical");
        }
        System.out.println("Time and space complexity is O(N) using recursive approach");

    }

    private boolean areIdenticalListRecursive(Node firstListHead, Node secondListHead) {

        if (firstListHead == null && secondListHead == null)
            return true;

        if (firstListHead != null && secondListHead != null)
            return firstListHead.getData() == secondListHead.getData() &&
                    areIdenticalListRecursive(firstListHead.getNext(), secondListHead.getNext());

        return false;
    }

    private boolean areIdenticalListIterative(Node firstListHead, Node secondListHead) {
        while (firstListHead != null && secondListHead != null) {
            if (firstListHead.getData() != secondListHead.getData())
                return false;
            firstListHead = firstListHead.getNext();
            secondListHead = secondListHead.getNext();
        }
        //If both pointers are null at this point means lists are identical
        return firstListHead == null && secondListHead == null;
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
