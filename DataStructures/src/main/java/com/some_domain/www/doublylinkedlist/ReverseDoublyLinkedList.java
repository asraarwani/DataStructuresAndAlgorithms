package com.some_domain.www.doublylinkedlist;

/**
 * @author : waniasra
 * @date : 2/5/2020 12:13 PM
 * This class demonstrates how to reverse a doubly linked list
 */
public class ReverseDoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public ReverseDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
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

    public static void main(String[] args) {

        ReverseDoublyLinkedList list = new ReverseDoublyLinkedList();

        list.insertNodeIntoDoublyLinkedListAtBeginning(10);
        list.insertNodeIntoDoublyLinkedListAtBeginning(9);
        list.insertNodeIntoDoublyLinkedListAtBeginning(8);
        list.insertNodeIntoDoublyLinkedListAtBeginning(7);
        list.insertNodeIntoDoublyLinkedListAtBeginning(6);
        list.insertNodeIntoDoublyLinkedListAtBeginning(5);
        list.insertNodeIntoDoublyLinkedListAtBeginning(4);
        list.insertNodeIntoDoublyLinkedListAtBeginning(3);
        list.insertNodeIntoDoublyLinkedListAtBeginning(2);
        list.insertNodeIntoDoublyLinkedListAtBeginning(1);

        System.out.println("Forward traversal of doubly linked list");
        list.displayContentsOfDoublyLinkedListUsingForwardTraversal(list.getHead());
        System.out.println("\nBackward traversal of doubly linked list");
        list.displayContentsOfDoublyLinkedListUsingBackwardTraversal(list.getTail());
        System.out.println("\nHead " + list.getHead().getData() + " Tail " + list.getTail().getData());

        Node newHead = list.reverseDoublyLinkedList(list.getHead());
        list.setTail(list.getHead()); // Head becomes the tail after reversing the doubly linked list
        list.setHead(newHead);        //Updating the head

        System.out.println("\n\nAfter reversing the doubly linked list\n");

        System.out.println("Forward traversal of doubly linked list");
        list.displayContentsOfDoublyLinkedListUsingForwardTraversal(list.getHead());
        System.out.println("\nBackward traversal of doubly linked list");
        list.displayContentsOfDoublyLinkedListUsingBackwardTraversal(list.getTail());
        System.out.println("\nHead " + list.getHead().getData() + " Tail " + list.getTail().getData());
    }

    public Node reverseDoublyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Doubly linked list is empty.");
            return null;
        } else {
            Node currentNode = headReference;
            Node nextNode = null;
            Node prevoiusNode = null;
            while (currentNode != null) {
                nextNode = currentNode.getNext();
                currentNode.setNext(prevoiusNode);
                prevoiusNode = currentNode;
                prevoiusNode.setPrevious(nextNode);
                currentNode = nextNode;
            }
            return prevoiusNode;
        }
    }

    public void displayContentsOfDoublyLinkedListUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Doubly linked list is empty.");
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void displayContentsOfDoublyLinkedListUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Doubly linked list is empty.");
        } else {
            while (tailReference != null) {
                System.out.print(tailReference.getData() + " ");
                tailReference = tailReference.getPrevious();
            }
        }
    }

    public void insertNodeIntoDoublyLinkedListAtBeginning(int data) {
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
