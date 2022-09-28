package com.some_domain.www.singlylinkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 28-09-2022 04:50 pm
 * References :
 * https://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/
 * https://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/
 */
public class CloneSinglyLinkedListWithRandomPointers {

    private Node head;


    public static void main(String[] args) {

        CloneSinglyLinkedListWithRandomPointers list = new CloneSinglyLinkedListWithRandomPointers();
        list.insertNodeAtEndOfSinglyLinkedList(1);
        list.insertNodeAtEndOfSinglyLinkedList(2);
        list.insertNodeAtEndOfSinglyLinkedList(3);
        list.insertNodeAtEndOfSinglyLinkedList(4);
        list.insertNodeAtEndOfSinglyLinkedList(5);
        list.insertNodeAtEndOfSinglyLinkedList(6);
        list.insertNodeAtEndOfSinglyLinkedList(7);
        list.insertNodeAtEndOfSinglyLinkedList(8);
        list.insertNodeAtEndOfSinglyLinkedList(9);
        list.insertNodeAtEndOfSinglyLinkedList(10);
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nAfter setting up some random references");
        list.setupRandomLinks(list.getHead());
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nClone list is given as follows");
        Node cloneListHead = list.cloneListUsingHashing(list.getHead());
        CloneSinglyLinkedListWithRandomPointers clone = new CloneSinglyLinkedListWithRandomPointers();
        clone.setHead(cloneListHead);
        clone.displayContentsOfSinglyLinkedList(clone.getHead());
        System.out.println("\nTime complexity and space complexity is O(N)");

        System.out.println("\nAlternate approach");
        Node newHead = list.cloneSinglyLinkedListOptimized(list.getHead());
        CloneSinglyLinkedListWithRandomPointers cloneList = new CloneSinglyLinkedListWithRandomPointers();
        cloneList.setHead(newHead);
        cloneList.displayContentsOfSinglyLinkedList(cloneList.getHead());
        System.out.println("\n\nTime complexity is O(N)");
    }

    private Node cloneListUsingHashing(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
            return null;
        } else {
            Node originalListCurrent = headReference;
            Node cloneListCurrent = null;
            Map<Node, Node> map = new HashMap<>();
            while (originalListCurrent != null) {
                cloneListCurrent = new Node(originalListCurrent.getData(), null);
                map.put(originalListCurrent, cloneListCurrent);
                originalListCurrent = originalListCurrent.getNext();
            }

            //Reset originalListCurrent to headReference - for setting the next and random links in cloned list
            originalListCurrent = headReference;
            while (originalListCurrent != null) {
                cloneListCurrent = map.get(originalListCurrent);
                cloneListCurrent.setNext(map.get(originalListCurrent.getNext()));
                cloneListCurrent.setRandom(map.get(originalListCurrent.getRandom()));
                originalListCurrent = originalListCurrent.getNext();
            }
            Node cloneListHead = map.get(headReference);
            return cloneListHead;
        }
    }

    private Node cloneSinglyLinkedListOptimized(Node headReference) {

        //Insert additional node after every node in singly linked list
        Node currentNode = headReference;
        Node temporaryNode = null;
        while (currentNode != null) {
            temporaryNode = currentNode.getNext();
            Node cloneNode = new Node(currentNode.getData(), null);
            currentNode.setNext(cloneNode);
            cloneNode.setNext(temporaryNode);
            currentNode = temporaryNode;
        }

        //Adjust the random links to the newly created nodes
        currentNode = headReference;
        while (currentNode != null) {
            if (currentNode.getNext() != null) {
                Node random = currentNode.getRandom() != null ? currentNode.getRandom().getNext() : currentNode.getRandom();
                currentNode.getNext().setRandom(random);
            }
            currentNode = currentNode.getNext().getNext();
        }

        //Now separate the original and cloned list
        currentNode = headReference;
        Node cloneCurrent = currentNode.getNext();
        Node cloneCurrentCopy = cloneCurrent;
        while (currentNode != null) {
            //Original list
            currentNode.setNext(currentNode.getNext().getNext());

            //Cloned list
            Node temp = cloneCurrent.getNext() != null ? cloneCurrent.getNext().getNext() : cloneCurrent.getNext();
            cloneCurrent.setNext(temp);

            //Updating the pointers
            currentNode = currentNode.getNext();
            cloneCurrent = cloneCurrent.getNext();
        }
        return cloneCurrentCopy;
    }

    public void setupRandomLinks(Node headReference) {
        headReference.setRandom(headReference.getNext().getNext().getNext().getNext());
        headReference.getNext().getNext().setRandom(headReference.getNext().getNext().getNext().getNext().getNext());
        headReference.getNext().getNext().getNext().getNext().getNext().getNext().setRandom(headReference.getNext());
        headReference.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setRandom(headReference.getNext());
        headReference.getNext().getNext().getNext().getNext().setRandom(headReference.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());
    }

    private void insertNodeAtEndOfSinglyLinkedList(int data) {
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

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            while (headReference != null) {
                if (headReference.getRandom() != null) {
                    System.out.print(headReference.getData() + "(" + headReference.getRandom().getData() + ") ");
                } else {
                    System.out.print(headReference.getData() + "(X)  ");
                }
                headReference = headReference.getNext();
            }
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
        private Node random;

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

        public Node getRandom() {
            return random;
        }

        public void setRandom(Node random) {
            this.random = random;
        }
    }
}
