package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 07-10-2022 12:53 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/move-last-element-to-front-of-a-given-linked-list/
 */
public class MoveLastNodeToFront {

    private Node head;

    public static void main(String[] args) {

        MoveLastNodeToFront list = new MoveLastNodeToFront();
        list.insertNodeIntoSLL(1);
        list.insertNodeIntoSLL(2);
        list.insertNodeIntoSLL(3);
        list.insertNodeIntoSLL(4);
        list.insertNodeIntoSLL(5);
        list.insertNodeIntoSLL(6);
        list.insertNodeIntoSLL(7);
        list.insertNodeIntoSLL(8);
        list.insertNodeIntoSLL(9);
        list.insertNodeIntoSLL(10);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nAfter moving last node to front of singly linked list. Time complexity is O(N)");
        list.moveLastNodeToFront(list.getHead());
        list.displayContentsOfSinglyLinkedList(list.getHead());
    }

    private void moveLastNodeToFront(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            Node previousNode = null;
            Node currentNode = headReference;
            while (currentNode.getNext() != null) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            previousNode.setNext(null); //Setting next of second last to null
            currentNode.setNext(headReference); //Pointing last node to head node of the list
            this.setHead(currentNode); // Updating head of the singly linked list
        }
    }

    private void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            Node currentNode = headReference;
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    private void insertNodeIntoSLL(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversalNode = head;
            while (traversalNode.getNext() != null) {
                traversalNode = traversalNode.getNext();
            }
            traversalNode.setNext(newNode);
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
