package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/14/2020 9:46 PM
 * This class demonstrates how to do a merge sort on a singly linked list
 */
public class MergeSortSinglyLinkedList {

    private Node head;
    private int size;

    public MergeSortSinglyLinkedList() {
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

        MergeSortSinglyLinkedList list = new MergeSortSinglyLinkedList();
        list.insertNodeAtBeginningOfSinglyLinkedList(10);
        list.insertNodeAtBeginningOfSinglyLinkedList(5);
        list.insertNodeAtBeginningOfSinglyLinkedList(6);
        list.insertNodeAtBeginningOfSinglyLinkedList(1);
        list.insertNodeAtBeginningOfSinglyLinkedList(2);
        list.insertNodeAtBeginningOfSinglyLinkedList(8);
        list.insertNodeAtBeginningOfSinglyLinkedList(9);
        list.insertNodeAtBeginningOfSinglyLinkedList(4);
        list.insertNodeAtBeginningOfSinglyLinkedList(3);
        list.insertNodeAtBeginningOfSinglyLinkedList(7);

        System.out.println("Before sorting");
        list.displayContentsOfSinglyLinkedList(list.getHead());

        Node newHead = list.mergeSort(list.getHead());
        list.setHead(newHead);

        System.out.println();
        System.out.println("After sorting");
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nTime complexity is O(NlogN)");
    }

    public Node mergeSort(Node headReference) {
        if (headReference == null || headReference.getNext() == null) {
            return headReference;
        } else {
            Node resultNode = null;
            Node middleNode = getMiddleNode(headReference);
            Node secondHalfHeadNodeRef = middleNode.getNext();
            middleNode.setNext(null);
            Node firstPartHeadNode = mergeSort(headReference);
            Node secondPartHeadNode = mergeSort(secondHalfHeadNodeRef);
            resultNode = merge(firstPartHeadNode, secondPartHeadNode);
            return resultNode;
        }
    }

    private Node merge(Node firstPartHeadNode, Node secondPartHeadNode) {
        Node resultNode = null;
        if (firstPartHeadNode == null)
            return secondPartHeadNode;
        else if (secondPartHeadNode == null)
            return firstPartHeadNode;
        else if (firstPartHeadNode.getData() <= secondPartHeadNode.getData()) {
            resultNode = firstPartHeadNode;
            resultNode.setNext(merge(firstPartHeadNode.getNext(), secondPartHeadNode));
        } else {
            resultNode = secondPartHeadNode;
            resultNode.setNext(merge(firstPartHeadNode, secondPartHeadNode.getNext()));
        }
        return resultNode;
    }

    private Node getMiddleNode(Node headReference) {
        if (headReference == null || headReference.getNext() == null)
            return headReference;
        else {
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            while (fastPointer != null && fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
            }
            return slowPointer;
        }
    }

    public void insertNodeAtBeginningOfSinglyLinkedList(int data) {
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
            System.out.println("Singly  linked  list is empty.");
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
