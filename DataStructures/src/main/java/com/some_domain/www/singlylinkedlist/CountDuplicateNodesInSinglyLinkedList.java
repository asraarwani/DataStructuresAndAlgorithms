package com.some_domain.www.singlylinkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : asraar
 * @date : 29-09-2022 11:17 am
 * <p>
 * Reference : https://www.geeksforgeeks.org/count-duplicates-in-a-given-linked-list/
 */
public class CountDuplicateNodesInSinglyLinkedList {

    private Node head;

    public static void main(String[] args) {

        CountDuplicateNodesInSinglyLinkedList list = new CountDuplicateNodesInSinglyLinkedList();
        list.insertNode(1);
        list.insertNode(2);
        list.insertNode(3);
        list.insertNode(4);
        list.insertNode(5);
        list.insertNode(6);
        list.insertNode(7);
        list.insertNode(2);
        list.insertNode(5);
        list.insertNode(10);
        list.displayContents(list.getHead());

        int duplicateNode = list.countDuplicateNodesInSinglyLinkedList(list.getHead());
        System.out.println();
        System.out.println("Number of duplicate nodes is : " + duplicateNode);
        System.out.println("Time and space complexity is O(N)");
    }

    public int countDuplicateNodesInSinglyLinkedList(Node headReference) {
        int duplicateNodeCount = 0;
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            Node currentNode = headReference;
            Set<Integer> set = new HashSet<>();
            while (currentNode != null) {
                if (set.contains(currentNode.getData())) {
                    duplicateNodeCount++;
                }
                set.add(currentNode.getData());
                currentNode = currentNode.getNext();
            }
        }
        return duplicateNodeCount;
    }

    public void insertNode(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null)
                currentNode = currentNode.getNext();
            currentNode.setNext(newNode);
        }
    }

    public void displayContents(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            do {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            } while (headReference != null);
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
