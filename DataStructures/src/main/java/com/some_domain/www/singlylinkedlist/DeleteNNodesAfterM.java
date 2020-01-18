package com.some_domain.www.singlylinkedlist;

import javax.swing.*;

/**
 * @author : waniasra
 * @date : 1/18/2020 8:26 PM
 * This class demonstrates how to delete N nodes after every M number of nodes
 */
//Reference : https://www.geeksforgeeks.org/delete-n-nodes-after-m-nodes-of-a-linked-list/
public class DeleteNNodesAfterM {

    private Node head;
    private int size;

    public DeleteNNodesAfterM() {
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

        DeleteNNodesAfterM list = new DeleteNNodesAfterM();
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

        int n = 1;
        int m = 4;
        System.out.println("\n\nAfter deleting " + n + " node(s) after every " + m + " node(s)");
        list.deleteNNodesAfterM(list.getHead(), n, m);
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println();
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in singly linked list");
    }

    public void deleteNNodesAfterM(Node headReference, int n, int m) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return;
        } else {
            Node currentNode = headReference;
            Node previousNode = null;
            int nodeCount = 0;
            while (currentNode != null) {

                nodeCount++;
                previousNode = currentNode;

                if (currentNode.getNext() != null && nodeCount == m) {
                    for (int i = 0; i < n; i++) {
                        currentNode = currentNode.getNext();
                    }
                    previousNode.setNext(currentNode.getNext());
                    nodeCount = 0;
                }
                currentNode = currentNode.getNext();
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
