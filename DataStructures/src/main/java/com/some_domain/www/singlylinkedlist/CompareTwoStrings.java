package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/18/2020 11:31 AM
 * This class demonstrates how to compare two strings represented by two singly linked lists
 */
//Reference : https://www.geeksforgeeks.org/compare-two-strings-represented-as-linked-lists/
public class CompareTwoStrings {

    private Node head;
    private int size;

    public CompareTwoStrings() {
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

        CompareTwoStrings firstString = new CompareTwoStrings();
        firstString.insertNodeAtEndOfSinglyLinkedList('a');
        firstString.insertNodeAtEndOfSinglyLinkedList('s');
        firstString.insertNodeAtEndOfSinglyLinkedList('r');
        firstString.insertNodeAtEndOfSinglyLinkedList('a');
        firstString.insertNodeAtEndOfSinglyLinkedList('a');
        firstString.insertNodeAtEndOfSinglyLinkedList('r');
        firstString.displayContentsOfSinglyLinkedList(firstString.getHead());

        System.out.println();
        CompareTwoStrings secondString = new CompareTwoStrings();
        secondString.insertNodeAtEndOfSinglyLinkedList('a');
        secondString.insertNodeAtEndOfSinglyLinkedList('s');
        secondString.insertNodeAtEndOfSinglyLinkedList('r');
        secondString.insertNodeAtEndOfSinglyLinkedList('a');
        secondString.insertNodeAtEndOfSinglyLinkedList('a');
        secondString.insertNodeAtEndOfSinglyLinkedList('r');
        secondString.displayContentsOfSinglyLinkedList(secondString.getHead());

        System.out.println();
        int comparison = new CompareTwoStrings().compareTwoStrings(firstString.getHead(), secondString.getHead());
        System.out.println("Result : " + comparison);
        System.out.println("Time complexity is O(min(N, M)) where N and M are the number of nodes in two singly linked lists");
    }

    public int compareTwoStrings(Node firstStringHead, Node secondStringHead) {
        if (firstStringHead == null && secondStringHead == null)
            return 0;

        //Process characters of the two string as long as they match
        while (firstStringHead != null && secondStringHead != null && firstStringHead.getCharacter() == secondStringHead.getCharacter()) {
            firstStringHead = firstStringHead.getNext();
            secondStringHead = secondStringHead.getNext();
        }

        //If characters of the two strings mismatch
        if (firstStringHead != null && secondStringHead != null) {
            return firstStringHead.getCharacter() > secondStringHead.getCharacter() ? 1 : -1;
        }
        //If firstString exhausts
        if (firstStringHead == null && secondStringHead != null) {
            return -1;
        }

        //If secondString exhausts
        if (firstStringHead != null && secondStringHead == null) {
            return 1;
        }

        return 0;
    }

    public void insertNodeAtEndOfSinglyLinkedList(char character) {
        Node newNode = new Node(character, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversingNode = head;
            while ((traversingNode.getNext() != null)) {
                traversingNode = traversingNode.getNext();
            }
            traversingNode.setNext(newNode);
        }
        size++;
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked representing string is empty");
            return;
        } else {
            while (headReference != null) {
                System.out.print(headReference.getCharacter() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    private class Node {

        private char character;
        private Node next;

        public Node(char character, Node next) {
            this.character = character;
            this.next = next;
        }

        public char getCharacter() {
            return character;
        }

        public void setCharacter(char character) {
            this.character = character;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
