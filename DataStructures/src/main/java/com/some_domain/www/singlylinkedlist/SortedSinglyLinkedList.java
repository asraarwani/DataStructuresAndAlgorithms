package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 10/10/2019 12:22 PM
 * This class demonstrates how to crate a sorted singly linked list
 */
public class SortedSinglyLinkedList {

    private Node head;
    private int size;


    public SortedSinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        SortedSinglyLinkedList list = new SortedSinglyLinkedList();

        //Inserting few nodes into sorted singly linked list
        list.insertNodeInSinglyLinkedListInOrder(5);
        list.insertNodeInSinglyLinkedListInOrder(1);
        list.insertNodeInSinglyLinkedListInOrder(4);
        list.insertNodeInSinglyLinkedListInOrder(6);
        list.insertNodeInSinglyLinkedListInOrder(3);
        list.insertNodeInSinglyLinkedListInOrder(30);

        System.out.println("Contents of the sorted singly linked list are as follows");
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nCurrent size of the sorted singly linked list is " + list.getSize() + " Head node is " + list.getHead().getData());
    }

    public void insertNodeInSinglyLinkedListInOrder(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else if (data < head.getData()) {  //In case node to be inserted is smaller than header node , we add it at the beginning and make it a head node
            newNode.setNext(head);
            head = newNode;
        } else {                            //In case node to be inserted fits somewhere in between head  and last node
            Node currentNode = head;
            Node nextNode = head.getNext();
            boolean nodeInseted = false;
            while (currentNode != null && currentNode.getNext() != null) {
                if (data >= currentNode.getData() && data <= nextNode.getData()) {
                    currentNode.setNext(newNode);
                    newNode.setNext(nextNode);
                    nodeInseted = true;
                    break;
                }
                currentNode = nextNode;
                nextNode = nextNode.getNext();
            }
            //In case node is not inserted (means it doesn't fit in between), we append it at the end
            if (!nodeInseted) {
                currentNode.setNext(newNode);
            }
        }
        size++;
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
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
