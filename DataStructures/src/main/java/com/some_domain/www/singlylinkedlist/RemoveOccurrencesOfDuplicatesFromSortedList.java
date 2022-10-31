package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 26-10-2022 05:14 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/remove-occurrences-duplicates-sorted-linked-list/
 */
public class RemoveOccurrencesOfDuplicatesFromSortedList {

    private Node head;

    public static void main(String[] args) {

        RemoveOccurrencesOfDuplicatesFromSortedList list = new RemoveOccurrencesOfDuplicatesFromSortedList();
        list.insertNodeAtEnd(1);
        list.insertNodeAtEnd(2);
        list.insertNodeAtEnd(3);
        list.insertNodeAtEnd(3);
        list.insertNodeAtEnd(2);
        list.insertNodeAtEnd(4);
        list.insertNodeAtEnd(5);
        list.insertNodeAtEnd(3);
        list.insertNodeAtEnd(5);
        list.insertNodeAtEnd(10);
        list.displayContents(list.getHead());

        System.out.println("\nAfter");
        list.removeDuplicateOccurrences(list.getHead());
        list.displayContents(list.getHead());
    }

    private void removeDuplicateOccurrences(Node headReference) {
        Node temporaryNode = new Node(Integer.MIN_VALUE, null);
        temporaryNode.setNext(headReference);
        Node previous = temporaryNode;
        Node currentNode = headReference;
        while (currentNode != null) {

            while (currentNode.getNext() != null && previous.getNext().getData() == currentNode.getNext().getData()) {
                currentNode = currentNode.getNext();
            }

            if (previous.getNext() == currentNode) {
                previous = previous.getNext();
            } else {
                previous.setNext(currentNode.getNext());
            }

            currentNode = currentNode.getNext();
        }
        this.setHead(temporaryNode.getNext());
    }

    private void insertNodeAtEnd(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else if (newNode.getData() < head.getData()) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node firstNode = head;
            Node secondNode = head.getNext();
            boolean inserted = false;
            Node currentNode = head;
            while (firstNode != null && secondNode != null && !inserted) {
                if (newNode.getData() > firstNode.getData() && newNode.getData() <= secondNode.getData()) {
                    firstNode.setNext(newNode);
                    newNode.setNext(secondNode);
                    inserted = true;
                } else {
                    firstNode = firstNode.getNext();
                    secondNode = secondNode.getNext();
                }
            }
            if (!inserted) {
                firstNode.setNext(newNode);
            }
        }
    }

    private void displayContents(Node headReference) {
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
