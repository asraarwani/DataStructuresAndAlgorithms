package com.some_domain.www.singlylinkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 29-09-2022 10:56 am
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-the-first-duplicate-element-in-the-linked-list/
 */
public class FindDuplicateElementInSinglyLinkedList {

    private Node head;

    public static void main(String[] args) {

        FindDuplicateElementInSinglyLinkedList list = new FindDuplicateElementInSinglyLinkedList();
        list.insertNode(1);
        list.insertNode(2);
        list.insertNode(3);
        list.insertNode(4);
        list.insertNode(5);
        list.insertNode(6);
        list.insertNode(7);
        list.insertNode(8);
        list.insertNode(5);
        list.insertNode(10);
        list.displayContents(list.getHead());

        int duplicateNode = list.findDuplicateElementInSinglyLinkedList(list.getHead());
        System.out.println();
        System.out.println("Duplicate node is : " + duplicateNode);
        System.out.println("Time complexity is O(N)");
    }

    public int findDuplicateElementInSinglyLinkedList(Node headReference) {
        int result = -1;
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            Node currentNode = headReference;
            while (currentNode != null) {
                if (map.containsKey(currentNode.getData())) {
                    map.put(currentNode.getData(), map.get(currentNode.getData()) + 1);
                } else {
                    map.put(currentNode.getData(), 1);
                }
                currentNode = currentNode.getNext();
            }
            currentNode = headReference;
            while (currentNode != null) {
                if (map.get(currentNode.getData()) > 1) {
                    return currentNode.data;
                }
                currentNode = currentNode.getNext();
            }
        }
        return result;
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
