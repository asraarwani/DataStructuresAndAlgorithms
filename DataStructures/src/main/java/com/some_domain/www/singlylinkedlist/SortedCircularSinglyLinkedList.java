package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 10/13/2019 7:04 PM
 * This class demonstrates how to create a sorted circular singly linked list
 */
public class SortedCircularSinglyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public SortedCircularSinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    public static void main(String[] args) {

        SortedCircularSinglyLinkedList list = new SortedCircularSinglyLinkedList();

        //Inserting few nodes into sorted circular singly linked list
        list.insertNodeIntoSortedCircularSinglyLinkedList(80);
        list.insertNodeIntoSortedCircularSinglyLinkedList(8);
        list.insertNodeIntoSortedCircularSinglyLinkedList(100);
        list.insertNodeIntoSortedCircularSinglyLinkedList(5);
        list.insertNodeIntoSortedCircularSinglyLinkedList(1);
        list.insertNodeIntoSortedCircularSinglyLinkedList(9);

        System.out.println("Contents of sorted circular singly linked list");
        list.printContentsOfSortedCircularSinglyLinkedListUsingForwardTraversal(list.getHead());

        System.out.println("\nCurrent size of the sorted circular singly linked list is : " + list.getSize() + " , Head node : " + list.getHead().getData());
    }

    private void printContentsOfSortedCircularSinglyLinkedListUsingForwardTraversal(Node headRreference) {
        if (headRreference == null) {
            System.out.println("Sorted circular singly linked list s empty.");
        } else {
            Node traversingNode = headRreference;
            do {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getNext();
            } while (traversingNode != headRreference);
        }
    }

    public void insertNodeIntoSortedCircularSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = tail = newNode;
        } else if (newNode.getData() < head.getData()) {
            newNode.setNext(head);
            head = newNode;
            tail.setNext(head);
        } else if (newNode.getData() > tail.getData()) { // Since we have reference to the tail node, we are using it to insert a new node at the end
            tail.setNext(newNode);
            tail = newNode;
            tail.setNext(head);
        } else {
            Node currentNode = head;
            Node nextNode = head.getNext();
            while (currentNode != null && currentNode.getNext() != null) {
                if (newNode.getData() >= currentNode.getData() && newNode.getData() <= nextNode.getData()) {
                    currentNode.setNext(newNode);
                    newNode.setNext(nextNode);
                    break; //Breaking out of the loop once new node is inserted at proper position
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
