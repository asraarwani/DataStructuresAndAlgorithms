package com.some_domain.www.doublylinkedlist;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

/**
 * @author : waniasra
 * @date : 10/9/2019 12:19 PM
 * This class demonstrates the basic operations on a doubly linked list
 */

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();
        boolean keepRunning = true;
        int nodeData = Integer.MIN_VALUE;
        while (keepRunning) {
            System.out.println("\n");
            System.out.println("1 for inserting a node at the beginning of the doubly linked list");
            System.out.println("2 for inserting a node at the end of the doubly linked list");
            System.out.println("3 for inserting a node at specific index of the doubly linked list");
            System.out.println("4 for deleting node from the beginning of the doubly linked list");
            System.out.println("5 for deleting node from the end of the doubly linked list");
            System.out.println("6 for deleting node from specific index of the doubly linked list");
            System.out.println("7 for searching for a given node form the doubly linked list");
            System.out.println("8 for displaying contents of the doubly linked list using forward traversal");
            System.out.println("9 for displaying contents of the doubly linked list using backward traversal");
            System.out.println("10 for clearing the current contents of the doubly linked list");
            System.out.println("0 for terminating the program");
            System.out.println("Enter your choice....");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the data of the node to be inserted at the beginning");
                    nodeData = in.nextInt();
                    list.insertNodeAtBeginningOfDoublyLinkedList(nodeData);
                    break;
                case 2:
                    System.out.println("Enter the data of the node to be inserted at the end");
                    nodeData = in.nextInt();
                    list.insertNodeAtEndOfDoublyLinkedList(nodeData);
                    break;
                case 3:
                    System.out.println("Enter the insertion index");
                    int insertionIndex = in.nextInt();
                    System.out.println("Enter the data of the node to be inserted at index " + insertionIndex);
                    nodeData = in.nextInt();
                    list.insertNodeAtSpecificIndexOfDoublyLinkedList(nodeData, insertionIndex);
                case 4:
                    list.deleteNodeFromBeginningOfDoublyLinkedList();
                    break;
                case 5:
                    list.deleteNodeFromEndOfDoublyLinkedList();
                    break;
                case 6:
                    System.out.println("Current size of the doubly linked list is " + list.getSize());
                    System.out.println("Enter the deleting index");
                    int deletionIndex = in.nextInt();
                    list.deleteNodeFromSpecificIndexOfDoublyLinkedList(deletionIndex);
                    break;
                case 7:
                    System.out.println("Enter the data of the node you want to search for");
                    nodeData = in.nextInt();
                    boolean isPresent = list.searchForGivenNodeInDoublyLinkedList(nodeData);
                    if (isPresent)
                        System.out.println(nodeData + " is present in the doubly linked list");
                    else
                        System.out.println(nodeData + " is NOT present in the doubly linked list");
                    break;
                case 8:
                    System.out.println("Contents of the doubly linked list using forward traversal are as follows");
                    list.displayContentsUsingForwardTraversal(list.getHead());
                    break;
                case 9:
                    System.out.println("Contents of the doubly linked list using backward traversal are as follows");
                    list.displayContentsUsingBackwardTraversal(list.getTail());
                    break;
                case 10:
                    list.clearCurrentContentsOfDoublyLinkedList();
                    break;
                case 0:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Input you have entered isn't valid!!!");
            }
        }
        in.close();
    }

    public void insertNodeAtBeginningOfDoublyLinkedList(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    public void insertNodeAtEndOfDoublyLinkedList(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }

    public void insertNodeAtSpecificIndexOfDoublyLinkedList(int data, int insertionIndex) {
        Node newNode = new Node(data, null, null);
        if (insertionIndex < 0 || insertionIndex > size) {
            System.out.println("Insertion at index " + insertionIndex + " is not possible.");
            return;
        } else if (insertionIndex == 0) {
            insertNodeAtBeginningOfDoublyLinkedList(data);
        } else if (insertionIndex == size) {
            insertNodeAtEndOfDoublyLinkedList(data);
        } else {
            Node traversalNode = head;
            for (int i = 0; i < insertionIndex - 1; i++) {
                traversalNode = traversalNode.getNext();
            }
            newNode.setNext(traversalNode.getNext());
            traversalNode.getNext().setPrevious(newNode);
            traversalNode.setNext(newNode);
            newNode.setPrevious(traversalNode);
            size++;
        }
    }

    public void deleteNodeFromBeginningOfDoublyLinkedList() {
        if (head == null) {
            System.out.println("Doubly linked list is empty.");
        } else {
            Node deletedNode = head;
            head = head.getNext();
            if (head != null)
                head.setPrevious(null);
            size--;
            System.out.println("Node deleted from the beginning of the doubly linked list is " + deletedNode.getData());
        }
    }

    public void deleteNodeFromEndOfDoublyLinkedList() {
        if (head == null) {
            System.out.println("Doubly linked list is empty.");
        } else {
            Node deletedNode = tail;
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
            System.out.println("Node deleted from the end of the doubly linked list is " + deletedNode.getData());
        }
    }

    public void deleteNodeFromSpecificIndexOfDoublyLinkedList(int deletionIndex) {
        if (deletionIndex < 1 || deletionIndex > size) {
            System.out.println("Deletion at index " + deletionIndex + " is not possible.");
        } else if (deletionIndex == 1) {
            deleteNodeFromBeginningOfDoublyLinkedList();
        } else if (deletionIndex == size) {
            deleteNodeFromEndOfDoublyLinkedList();
        } else {
            Node traversalNode = head;
            for (int i = 0; i < deletionIndex - 1; i++) {
                traversalNode = traversalNode.getNext();
            }
            Node deletedNode = traversalNode.getNext();
            traversalNode.setNext(traversalNode.getNext().getNext());
            traversalNode.getNext().setPrevious(traversalNode);
            size--;
        }
    }

    public boolean searchForGivenNodeInDoublyLinkedList(int key) {
        if (head == null) {
            System.out.println("Doubly linked  list is empty.");
            return false;
        } else {
            Node traversalNode = head;
            while (traversalNode != null) {
                if (traversalNode.getData() == key) {
                    return true;
                }
                traversalNode = traversalNode.getNext();
            }
            return false;
        }
    }

    public void displayContentsUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Doubly linked list is empty.");
        } else {
            System.out.print("[ ");
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
            System.out.print(" ]");
        }
    }

    public void displayContentsUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Doubly linked list is empty.");
        } else {
            System.out.print("[ ");
            while (tailReference != null) {
                System.out.print(tailReference.getData() + " ");
                tailReference = tailReference.getPrevious();
            }
            System.out.print(" ]");
        }
    }

    public void clearCurrentContentsOfDoublyLinkedList() {
        head = tail = null;
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
