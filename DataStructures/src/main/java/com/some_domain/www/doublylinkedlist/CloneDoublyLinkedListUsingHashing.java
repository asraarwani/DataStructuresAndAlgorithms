package com.some_domain.www.doublylinkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 2/11/2020 2:13 PM
 * This class demonstrates how to clone a doubly linked list with random pointers
 */
//Reference : https://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/
public class CloneDoublyLinkedListUsingHashing {

    private Node head;
    private int size;

    public CloneDoublyLinkedListUsingHashing() {
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

        CloneDoublyLinkedListUsingHashing list = new CloneDoublyLinkedListUsingHashing();

        list.insertNodeIntoDoublyLinkedListAtEnd(10);
        list.insertNodeIntoDoublyLinkedListAtEnd(20);
        list.insertNodeIntoDoublyLinkedListAtEnd(30);
        list.insertNodeIntoDoublyLinkedListAtEnd(40);
        list.insertNodeIntoDoublyLinkedListAtEnd(50);
        list.insertNodeIntoDoublyLinkedListAtEnd(60);
        list.insertNodeIntoDoublyLinkedListAtEnd(70);
        list.insertNodeIntoDoublyLinkedListAtEnd(80);
        list.insertNodeIntoDoublyLinkedListAtEnd(90);
        list.insertNodeIntoDoublyLinkedListAtEnd(100);

        list.displayContents(list.getHead());


        System.out.println("\nAfter setting up some random references");
        list.setupRandomReferences(list.getHead());
        list.displayContents(list.getHead());


        System.out.println();
        System.out.println("\nCloned list looks as follows");
        Node clonedListHead = list.clone(list.getHead());
        list.displayContents(clonedListHead);

        System.out.println("\n\nOriginal list");
        list.displayContents(list.getHead());

        System.out.println("\n\nTime complexity is O(N) and space complexity is O(N)");
    }

    public Node clone(Node headReference) {
        if (headReference == null) {
            System.out.println("Doubly linked list is empty");
            return null;
        } else {
            Map<Node, Node> hashMap = new LinkedHashMap<>();
            Node currentNode = headReference;
            Node cloneNode = null;
            while (currentNode != null) {
                cloneNode = new Node(currentNode.getData(), null, null);
                hashMap.put(currentNode, cloneNode);
                currentNode = currentNode.getNext();
            }
            currentNode = headReference;
            while (currentNode != null) {
                Node cloneNodeCurrent = hashMap.get(currentNode);
                //Setting the next and random links on cloneNode
                cloneNodeCurrent.setNext(currentNode.getNext());
                cloneNodeCurrent.setRandom(currentNode.getRandom());
                currentNode = currentNode.getNext();
            }
            return hashMap.get(headReference); // Contains reference to the first node in the cloned list
        }
    }

    public void displayContents(Node headReference) {
        if (headReference == null) {
            System.out.println("Doubly linked list is empty");
            return;
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

    public void setupRandomReferences(Node headReference) {
        headReference.setRandom(headReference.getNext().getNext().getNext().getNext());
        headReference.getNext().getNext().setRandom(headReference.getNext().getNext().getNext().getNext().getNext());
        headReference.getNext().getNext().getNext().getNext().getNext().getNext().setRandom(headReference.getNext());
        headReference.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setRandom(headReference.getNext());
        headReference.getNext().getNext().getNext().getNext().setRandom(headReference.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());
    }

    public void insertNodeIntoDoublyLinkedListAtEnd(int data) {
        Node newNode = new Node(data, null, null);
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
        private Node random;

        public Node(int data, Node next, Node random) {
            this.data = data;
            this.next = next;
            this.random = random;
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
