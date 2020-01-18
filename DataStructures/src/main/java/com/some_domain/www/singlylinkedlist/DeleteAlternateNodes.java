package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/18/2020 8:26 PM
 * This class demonstrates how to delete alternate nodes from singly linked list
 */
//Reference : https://www.geeksforgeeks.org/delete-alternate-nodes-of-a-linked-list/
public class DeleteAlternateNodes {

    private Node head;
    private int size;

    public DeleteAlternateNodes() {
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

        DeleteAlternateNodes list = new DeleteAlternateNodes();
        list.insertNodeIntoSinglyLinkedListAtEnd(10);
        list.insertNodeIntoSinglyLinkedListAtEnd(20);
        list.insertNodeIntoSinglyLinkedListAtEnd(30);
        list.insertNodeIntoSinglyLinkedListAtEnd(40);
        list.insertNodeIntoSinglyLinkedListAtEnd(50);
        list.insertNodeIntoSinglyLinkedListAtEnd(60);
        list.insertNodeIntoSinglyLinkedListAtEnd(70);
        list.insertNodeIntoSinglyLinkedListAtEnd(80);
        list.insertNodeIntoSinglyLinkedListAtEnd(90);
        list.insertNodeIntoSinglyLinkedListAtEnd(100);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nAfter deleting alternate nodes from singly linked list (Iterative)");
        list.deleteAlternateNodesFromSinglyLinkedListIterative(list.getHead());
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println();
        System.out.println("\nAfter deleting alternate nodes from singly linked list (Recursive)");
        list.deleteAlternateNodesFromSinglyLinkedListIterative(list.getHead());
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println();
        System.out.println("\nTime complexity for above two approaches is O(N) where N is the number of nodes in singly linked list");
    }

    public void deleteAlternateNodesFromSinglyLinkedListRecursive(Node headReference) {

        if (headReference == null)
            return;

        Node nextNode = headReference.getNext();
        if (nextNode == null)
            return;

        headReference.setNext(nextNode.getNext());
        deleteAlternateNodesFromSinglyLinkedListRecursive(nextNode.getNext());
    }

    public void deleteAlternateNodesFromSinglyLinkedListIterative(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return;
        } else {
            Node currentNode = head;
            Node nextNode = null;
            while (currentNode != null && currentNode.getNext() != null) {
                nextNode = currentNode.getNext();
                currentNode.setNext(nextNode.getNext());
                currentNode = nextNode;
            }
        }
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return;
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void insertNodeIntoSinglyLinkedListAtEnd(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversingNode = head;
            while (traversingNode.getNext() != null) {
                traversingNode = traversingNode.getNext();
            }
            traversingNode.setNext(newNode);
        }
        size++;
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
