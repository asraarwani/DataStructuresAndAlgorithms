package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 07-10-2022 08:52 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/make-middle-node-head-linked-list/
 */
public class MiddleNodeSetAsHead {

    private Node head;

    public static void main(String[] args) {

        MiddleNodeSetAsHead list = new MiddleNodeSetAsHead();
        list.insertNodeAtEnd(1);
        list.insertNodeAtEnd(2);
        list.insertNodeAtEnd(3);
        list.insertNodeAtEnd(4);
        list.insertNodeAtEnd(5);
        list.insertNodeAtEnd(6);
        list.insertNodeAtEnd(7);
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println();
        list.setMiddleNodeAsHeadOfSinglyLinkedList(list.getHead());
        System.out.println("After making middle node as a HEAD of singly linked list. Time complexity is O(N)");
        list.displayContentsOfSinglyLinkedList(list.getHead());
    }

    private void setMiddleNodeAsHeadOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            Node previousNode = null;
            while (fastPointer != null && fastPointer.getNext() != null) {
                previousNode = slowPointer;
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
            }
            //Slow pointer will be pointing to the middle node here.
            if (previousNode != null) {  // Null check if we have nodes less than 3
                previousNode.setNext(previousNode.getNext().getNext());
                slowPointer.setNext(headReference);
                this.head = slowPointer;
            }
        }
    }

    public void insertNodeAtEnd(int data) {
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

    private void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            do {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            } while (headReference != null);
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
