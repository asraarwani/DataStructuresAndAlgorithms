package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/21/2020 11:23 AM
 * This class demonstrates how to delete all the nodes smaller than X from a singly linked list
 */
//Reference : https://www.geeksforgeeks.org/delete-all-the-nodes-from-the-list-which-are-less-than-k/
public class DeleteAllNodesSmallerThanX {

    private Node head;
    private int size;

    public DeleteAllNodesSmallerThanX() {
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

        DeleteAllNodesSmallerThanX list = new DeleteAllNodesSmallerThanX();
        list.insertNodeIntoSinlgyLinkedListAtEnd(1);
        list.insertNodeIntoSinlgyLinkedListAtEnd(2);
        list.insertNodeIntoSinlgyLinkedListAtEnd(8);
        list.insertNodeIntoSinlgyLinkedListAtEnd(15);
        list.insertNodeIntoSinlgyLinkedListAtEnd(4);
        list.insertNodeIntoSinlgyLinkedListAtEnd(6);
        list.insertNodeIntoSinlgyLinkedListAtEnd(9);
        list.insertNodeIntoSinlgyLinkedListAtEnd(4);
        list.insertNodeIntoSinlgyLinkedListAtEnd(5);
        list.insertNodeIntoSinlgyLinkedListAtEnd(3);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        int x = 5;
        Node newHead = list.deleteAllNodesSmallerThanX(list.getHead(), x);
        list.setHead(newHead);

        System.out.println("\nAfter deleting all nodes smaller than " + x);
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nTime complexity is O(N) where N is the number of nodes in singly linked list");
    }

    public Node deleteAllNodesSmallerThanX(Node headReference, int x) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return null;
        } else {

            //If head node itself has smaller value
            while (headReference != null && headReference.getData() < x) {
                headReference = headReference.getNext();
            }

            //Deleting nodes other than head node
            Node previousNode = null;
            Node temporaryHead = headReference;
            while (headReference != null) {

                //Move to the next node unless node data is smaller
                while (headReference != null && headReference.getData() >= x) {
                    previousNode = headReference;
                    headReference = headReference.getNext();
                }

                if (headReference == null) {
                    return temporaryHead;
                }

                previousNode.setNext(headReference.getNext());
                headReference = previousNode.getNext();
            }
            return temporaryHead;
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
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
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
