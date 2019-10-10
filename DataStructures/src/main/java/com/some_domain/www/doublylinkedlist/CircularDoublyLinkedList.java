package com.some_domain.www.doublylinkedlist;

/**
 * @author : waniasra
 * @date : 10/10/2019 12:38 PM
 * This class demonstrates how to crate a circular doubly linked list
 */
// By circular, as we all know, the last node points to the first node
public class CircularDoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public CircularDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        CircularDoublyLinkedList list = new CircularDoublyLinkedList();

        //Inserting few nodes at the start of the circular doubly liked list
        list.insertNodeAtBeginningOfCircularDoublyLinkedList(20);
        list.insertNodeAtBeginningOfCircularDoublyLinkedList(10);
        list.insertNodeAtBeginningOfCircularDoublyLinkedList(5);
        list.insertNodeAtBeginningOfCircularDoublyLinkedList(4);
        list.insertNodeAtBeginningOfCircularDoublyLinkedList(2);
        System.out.println("Contents of circular doubly linked list using forward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingForwardTraversal(list.getHead());
        System.out.println("\nContents of circular doubly linked list using backward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingBackwardTraversal(list.getTail());
        System.out.println("\nCurrent size of the circular doubly linked list is : " + list.getSize() + " Head : " + list.getHead().getData() + " Tail : " + list.getTail().getData());

        System.out.println();
        System.out.println("\nAfter insertig few nodes at the end of the circular doubly linked list");
        list.insertNodeAtEndOfCircularDoublyLinkedList(100);
        list.insertNodeAtEndOfCircularDoublyLinkedList(200);
        list.insertNodeAtEndOfCircularDoublyLinkedList(300);
        list.insertNodeAtEndOfCircularDoublyLinkedList(400);
        System.out.println("Contents of circular doubly linked list using forward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingForwardTraversal(list.getHead());
        System.out.println("\nContents of circular doubly linked list using backward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingBackwardTraversal(list.getTail());
        System.out.println("\nCurrent size of the circular doubly linked list is : " + list.getSize() + " Head : " + list.getHead().getData() + " Tail : " + list.getTail().getData());

        System.out.println();
        //Deleting few nodes from the beginning of the circular doubly linked list
        list.deleteNodeFromBeginningOfCircularDoublyLinkedList();
        list.deleteNodeFromBeginningOfCircularDoublyLinkedList();
        list.deleteNodeFromBeginningOfCircularDoublyLinkedList();
        System.out.println("Contents of circular doubly linked list using forward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingForwardTraversal(list.getHead());
        System.out.println("\nContents of circular doubly linked list using backward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingBackwardTraversal(list.getTail());
        System.out.println("\nCurrent size of the circular doubly linked list is : " + list.getSize() + " Head : " + list.getHead().getData() + " Tail : " + list.getTail().getData());

        System.out.println();
        //Deleting few nodes from the end of the circular doubly linked list
        list.deleteNodeFromEndOfCircularDoublyLinkedList();
        list.deleteNodeFromEndOfCircularDoublyLinkedList();
        list.deleteNodeFromEndOfCircularDoublyLinkedList();
        System.out.println("Contents of circular doubly linked list using forward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingForwardTraversal(list.getHead());
        System.out.println("\nContents of circular doubly linked list using backward traversal");
        list.displayContentsOfCircularDoublyLinkedListUsingBackwardTraversal(list.getTail());
        System.out.println("\nCurrent size of the circular doubly linked list is : " + list.getSize() + " Head : " + list.getHead().getData() + " Tail : " + list.getTail().getData());
    }

    public void deleteNodeFromSpecificIndexOfCircularDoublyLinkedList(int deletionIndex) {
        //This operation is going to be similar to deleting a node in doubly linked list at specific index
        // as shown in the doubly linked list program. Link is given as follows:
        // https://github.com/asraarwani/DataStructuresAndAlgorithms/blob/master/DataStructures/src/main/java/com/some_domain/www/doublylinkedlist/DoublyLinkedList.java
    }

    public void deleteNodeFromEndOfCircularDoublyLinkedList() {
        if (head == null) {
            System.out.println("Circular doubly linked list is empty");
        } else {
            Node deletedNode = tail;
            tail = tail.getPrevious();
            tail.setNext(head);
            head.setPrevious(tail);
            System.out.println("\nNode deleted from the end of the circular doubly linked list is " + deletedNode.getData());
        }
    }

    public void deleteNodeFromBeginningOfCircularDoublyLinkedList() {
        if (head == null) {
            System.out.println("Circular doubly linked list is empty");
        } else {
            Node deletedNode = head;
            head = head.getNext();
            tail.setNext(head);
            head.setPrevious(tail);
            System.out.println("\nNode deleted from the beginning of the circular doubly linked list is " + deletedNode.getData());
        }
    }

    public void insertNodeAtSpecificIndexInCircularDoublyLinkedList(int data, int insertionIndex) {
        //This operation is going to be similar to inserting a node in doubly linked list at specific index
        // as shown in the doubly linked list program. Link is given as follows:
        // https://github.com/asraarwani/DataStructuresAndAlgorithms/blob/master/DataStructures/src/main/java/com/some_domain/www/doublylinkedlist/DoublyLinkedList.java
    }

    public void insertNodeAtEndOfCircularDoublyLinkedList(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
            // Doesn't make much sense here when we have only one node in the list
            tail.setNext(head);
            head.setPrevious(tail);
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            //Setting the link from tail to head and back to tail
            tail.setNext(head);
            head.setPrevious(getTail());
        }
        size++;
    }

    public void insertNodeAtBeginningOfCircularDoublyLinkedList(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
            // Doesn't make much sense here when we have only one node in the list
            tail.setNext(head);
            head.setPrevious(tail);
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            //Setting the link from tail to head and back to tail
            tail.setNext(head);
            head.setPrevious(tail);
        }
        size++;
    }

    public void displayContentsOfCircularDoublyLinkedListUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Circular doubly linked list is empty.");
        } else {
            Node traversingNode = headReference;
            do {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getNext();
            } while (traversingNode != headReference);
        }
    }

    public void displayContentsOfCircularDoublyLinkedListUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Circular doubly linked list is empty.");
        } else {
            Node traversingNode = tailReference;
            do {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getPrevious();
            } while (traversingNode != tailReference);
        }
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
