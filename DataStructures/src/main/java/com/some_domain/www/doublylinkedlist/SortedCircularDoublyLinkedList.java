package com.some_domain.www.doublylinkedlist;

/**
 * @author : waniasra
 * @date : 10/13/2019 6:59 PM
 * This class demonstrates how to create a sorted circular doubly linked list
 */
public class SortedCircularDoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public SortedCircularDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        SortedCircularDoublyLinkedList list = new SortedCircularDoublyLinkedList();

        //Inserting few nodes into sorted circular doubly linked list
        list.insertNodeInSortedOrder(5);
        list.insertNodeInSortedOrder(50);
        list.insertNodeInSortedOrder(1);
        list.insertNodeInSortedOrder(20);
        list.insertNodeInSortedOrder(9);
        list.insertNodeInSortedOrder(7);

        System.out.println("Contents of sorted circular doubly linked list using forward traversal");
        list.printContentsOfSortedCircularDoublyLinkedListUsingForwardTraversal(list.getHead());
        System.out.println("\nContents of sorted circular doubly linked list using backward traversal");
        list.printContentsOfSortedCircularDoublyLinkedListUsingBackwardTraversal(list.getTail());

        System.out.println("\nCurrent size of the sorted circular doubly linked list is : " + list.getSize() + " ,Head  : " + list.getHead().getData() + " ,Tail : " + list.getTail().getData());
    }

    public void printContentsOfSortedCircularDoublyLinkedListUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Sorted circular doubly linked list is empty.");
        } else {
            Node traversingNode = headReference;
            do {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getNext();
            } while (traversingNode != headReference);
        }
    }

    public void printContentsOfSortedCircularDoublyLinkedListUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Sorted circular doubly linked list is empty.");
        } else {
            Node traversingNode = tailReference;
            do {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getPrevious();
            } while (traversingNode != tailReference);
        }
    }

    public void insertNodeInSortedOrder(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
        } else if (newNode.getData() < head.getData()) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            //Setting the link from the last node to first node and back to last node
            tail.setNext(head);
            head.setPrevious(tail);
        } else if (newNode.getData() > tail.getData()) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            tail.setNext(head);
            head.setPrevious(tail);
        } else {
            Node currentNode = head;
            Node nextNode = head.getNext();
            while (currentNode != null && currentNode.getNext() != null) {
                if (newNode.getData() >= currentNode.getData() && newNode.getData() <= nextNode.getData()) {
                    currentNode.setNext(newNode);
                    newNode.setPrevious(currentNode);
                    newNode.setNext(nextNode);
                    nextNode.setPrevious(newNode);
                    break; //Breaking out of the loop once node is inserted at proper position
                }
                currentNode = nextNode;
                nextNode = nextNode.getNext();
            }
        }
        size++;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private class Node {

        private int data;
        private Node next;
        private Node previous;

        public Node(int data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }
}
