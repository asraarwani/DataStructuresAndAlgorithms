package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 28-09-2022 02:19 pm
 * Reference : https://www.geeksforgeeks.org/delete-nth-node-from-the-end-of-the-given-linked-list/
 */
public class RemoveNthNodeFromEnd {

    private Node head;

    public static void main(String[] args) {

        RemoveNthNodeFromEnd list = new RemoveNthNodeFromEnd();
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

        int n = 2;
        System.out.println("\nAfter deleting node fro index " + n + " from end");
        Node newHead = list.deleteNthFromEnd(list.getHead(), n);
        list.setHead(newHead);
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nTime complexity is O(N)");
    }

    private Node deleteNthFromEnd(Node headReference, int n) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
            return null;
        } else {
            Node fastPointer = headReference;
            Node slowPointer = headReference;

            //Move fastPointer n steps ahead from beginning
            for (int i = 0; i < n; i++) {
                fastPointer = fastPointer.getNext();
            }

            //If n is equal to the number of nodes in list - head node deletion
            if (fastPointer == null) {
                headReference = headReference.getNext();
                return headReference;
            }

            //By the time fastPointer reaches the end of the list, slowPointer is pointing to nth node from end
            while (fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext();
            }

            //Delete the node slowPointer is pointing to
            slowPointer.setNext(slowPointer.getNext().getNext());
            return headReference;
        }
    }

    private void insertNodeAtEndOfSinglyLinkedList(int data) {
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
            System.out.println("Singly linked list is empty");
        } else {
            Node traversalNode = headReference;
            do {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getNext();
            } while (traversalNode != null);
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
