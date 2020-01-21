package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/21/2020 11:18 AM
 * This class demonstrates how to delete first occurrence of a node in singly linked list
 */
public class DeleteFirstOccurrenceOfNode {

    private Node head;
    private int size;

    public DeleteFirstOccurrenceOfNode() {
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

        DeleteFirstOccurrenceOfNode list = new DeleteFirstOccurrenceOfNode();
        list.insertNodeIntoSinlgyLinkedListAtEnd(1);
        list.insertNodeIntoSinlgyLinkedListAtEnd(2);
        list.insertNodeIntoSinlgyLinkedListAtEnd(8);
        list.insertNodeIntoSinlgyLinkedListAtEnd(15);
        list.insertNodeIntoSinlgyLinkedListAtEnd(2);
        list.insertNodeIntoSinlgyLinkedListAtEnd(6);
        list.insertNodeIntoSinlgyLinkedListAtEnd(1);
        list.insertNodeIntoSinlgyLinkedListAtEnd(13);
        list.insertNodeIntoSinlgyLinkedListAtEnd(5);
        list.insertNodeIntoSinlgyLinkedListAtEnd(6);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println();
        int x = 1;
        Node newHead = list.deleteFirstOccurrenceOfGivenNode(list.getHead(), x);
        list.setHead(newHead);

        System.out.println("After deleting first occurrence of node " + x);
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nTime complexity is O(N) where N is the number of nodes in singly linked list");
    }

    public Node deleteFirstOccurrenceOfGivenNode(Node headReference, int givenNode) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return null;
        } else {

            //If givenNode is the head node in singly linked list
            if (headReference.getData() == givenNode) {
                headReference = headReference.getNext();
                return headReference;
            }

            //If node to be deleted is not the head node
            Node temporaryHeadReference = headReference;
            Node previousNode = headReference;
            boolean found = false;
            while (headReference != null && !found) {
                if (headReference.getData() == givenNode) {
                    previousNode.setNext(headReference.getNext());
                    found = true;
                } else {
                    previousNode = headReference;
                    headReference = headReference.getNext();
                }
            }
            return temporaryHeadReference;
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

    public void insertNodeIntoSinlgyLinkedListAtEnd(int data) {
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
        size++;
    }

    private class Node {

        private int data;
        private Node next;

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

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;


        }
    }
}
