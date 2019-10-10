package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 10/10/2019 10:16 PM
 * This class demonstrates how to create a circular singly linked list
 */

/**
 * A circular linked list is a linked list in which the last node points to the head or front node making the data structure to look like a circle.
 */
public class CircularSinglyLinkedList {

    private Node head;
    private Node tailNodeReference;
    private int size;

    public CircularSinglyLinkedList() {
        this.head = null;
        this.tailNodeReference = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        CircularSinglyLinkedList list = new CircularSinglyLinkedList();

        //Inserting few nodes at the beginning/start of the circular singly linked list
        list.insertNodeAtBeginningOfCircularSinglyLinkedList(10);
        list.insertNodeAtBeginningOfCircularSinglyLinkedList(9);
        list.insertNodeAtBeginningOfCircularSinglyLinkedList(8);
        list.insertNodeAtBeginningOfCircularSinglyLinkedList(7);
        list.insertNodeAtBeginningOfCircularSinglyLinkedList(6);
        list.displayContentsOfCircularSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of circular singly linked list is " + list.getSize() + " , Head node " + list.getHead().getData());

        //Inserting few node at the end of the circular singly linked list
        list.insertNodeAtEndOfCircularSinglyLinkedList(11);
        list.insertNodeAtEndOfCircularSinglyLinkedList(12);
        list.insertNodeAtEndOfCircularSinglyLinkedList(13);
        list.insertNodeAtEndOfCircularSinglyLinkedList(14);
        list.insertNodeAtEndOfCircularSinglyLinkedList(15);
        list.displayContentsOfCircularSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of circular singly linked list is " + list.getSize() + " , Head node " + list.getHead().getData());

        //Deleting few nodes from the beginning/start of the circular singly linked list
        list.deleteNodeFromBeginningOfCircularSinglyLinkedList();
        list.deleteNodeFromBeginningOfCircularSinglyLinkedList();
        list.deleteNodeFromBeginningOfCircularSinglyLinkedList();
        list.displayContentsOfCircularSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of circular singly linked list is " + list.getSize() + " , Head node " + list.getHead().getData());

        //Deleting few nodes from the end of the circular singly linked list
        list.deleteNodeFromEndOfCircularSinglyLinkedList();
        list.deleteNodeFromEndOfCircularSinglyLinkedList();
        list.deleteNodeFromEndOfCircularSinglyLinkedList();
        list.displayContentsOfCircularSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of circular singly linked list is " + list.getSize() + " , Head node " + list.getHead().getData());
    }

    public void deleteNodeFromBeginningOfCircularSinglyLinkedList() {
        if (head == null) {
            System.out.println("Circular singly linked list is empty.");
        } else {
            Node deletedNode = head;
            head = head.getNext();
            tailNodeReference.setNext(head);
            System.out.println("Node deleted from the beginning of the circular singly linked list is " + deletedNode.getData());
            size--;
        }
    }

    public void deleteNodeFromEndOfCircularSinglyLinkedList() {
        if (head == null) {
            System.out.println("Circular singly linked list is empty.");
        } else {
            Node traversingNode = head;
            Node previousNode = head;
            while (traversingNode.getNext() != head) {
                previousNode = traversingNode;
                traversingNode = traversingNode.getNext();
            }
            System.out.println("Node deleted from the end of the circular singly linked list is " + traversingNode.getData());
            tailNodeReference = previousNode;
            tailNodeReference.setNext(head);
            size--;
        }
    }

    public void deleteNodeFromSpecificIndexInCircularSinglyLinkedList(int deletionIndex) {
        //This operation is similar to deleting a node from singly linked list as shown in the following example:
        //https://github.com/asraarwani/DataStructuresAndAlgorithms/blob/master/DataStructures/src/main/java/com/some_domain/www/singlylinkedlist/SinglyLinkedList.java

    }

    public void insertNodeAtSpecificIndexInCircularSinglyLinkedList(int insertionIndex) {
        //This operation is similar to inserting a node from singly linked list as shown in the following example:
        //https://github.com/asraarwani/DataStructuresAndAlgorithms/blob/master/DataStructures/src/main/java/com/some_domain/www/singlylinkedlist/SinglyLinkedList.java
    }

    public void insertNodeAtEndOfCircularSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            System.out.println("Circular singly linked list is empty.");
        } else {
            //Since we have reference to  the last node of the circular singly linked list, we are going to use it to add a node at the end
            tailNodeReference.setNext(newNode);
            tailNodeReference = newNode;
            //Setting the link from last to head node, hence making the singly linked list a circular singly linked list
            tailNodeReference.setNext(head);
            size++;
        }
    }

    public void insertNodeAtBeginningOfCircularSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = tailNodeReference = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
            //Setting the link from last to head node, hence making the singly linked list a circular singly linked list
            tailNodeReference.setNext(head);
        }
        size++;
    }

    public void displayContentsOfCircularSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Circular singly linked list is empty.");
        } else {
            Node traversingNode = headReference;
            do {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getNext();
            } while (traversingNode != headReference);
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTailNodeReference() {
        return tailNodeReference;
    }

    public void setTailNodeReference(Node tailNodeReference) {
        this.tailNodeReference = tailNodeReference;
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
