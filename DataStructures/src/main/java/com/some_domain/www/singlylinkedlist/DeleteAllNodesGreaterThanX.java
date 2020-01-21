package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/21/2020 11:18 AM
 * This class demonstrates how to delete all the nodes greater than X from a singly linked list
 */
// Reference : https://www.geeksforgeeks.org/delete-nodes-list-greater-x/
public class DeleteAllNodesGreaterThanX {

    private Node head;
    private int size;

    public DeleteAllNodesGreaterThanX() {
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

        DeleteAllNodesGreaterThanX list = new DeleteAllNodesGreaterThanX();
        list.insertNodeIntoSinlgyLinkedListAtEnd(1);
        list.insertNodeIntoSinlgyLinkedListAtEnd(2);
        list.insertNodeIntoSinlgyLinkedListAtEnd(8);
        list.insertNodeIntoSinlgyLinkedListAtEnd(15);
        list.insertNodeIntoSinlgyLinkedListAtEnd(4);
        list.insertNodeIntoSinlgyLinkedListAtEnd(6);
        list.insertNodeIntoSinlgyLinkedListAtEnd(9);
        list.insertNodeIntoSinlgyLinkedListAtEnd(13);
        list.insertNodeIntoSinlgyLinkedListAtEnd(5);
        list.insertNodeIntoSinlgyLinkedListAtEnd(3);

        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println();
        int x = 10;
        Node newHead = list.deleteAllNodesGreaterThanX(list.getHead(), x);
        list.setHead(newHead);

        System.out.println("After deleting all the nodes greater than " + x);
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nTime complexity is O(N) where N is the number of nodes in singly linked list");
    }

    public Node deleteAllNodesGreaterThanX(Node headReference, int x) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return null;
        } else {

            //If head node itself is greater than x
            while (headReference != null && headReference.getData() > x) {
                headReference = headReference.getNext();
            }

            Node previousNode = null;
            Node temporaryHeadRef = headReference;

            while (headReference != null) {

                //Move headReference to the next node unless it is not greater than x
                while (headReference != null && headReference.getData() <= x) {
                    previousNode = headReference;
                    headReference = headReference.getNext();
                }

                //In case we reach to the end of the singly linked list
                if (headReference == null) {
                    return temporaryHeadRef;
                }

                previousNode.setNext(headReference.getNext());
                headReference = previousNode.getNext();
            }
            return temporaryHeadRef;
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
