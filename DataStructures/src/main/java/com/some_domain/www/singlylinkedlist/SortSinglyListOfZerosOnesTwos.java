package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 18-10-2022 09:28 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/sort-a-linked-list-of-0s-1s-or-2s/
 */
public class SortSinglyListOfZerosOnesTwos {

    private Node head;

    public static void main(String[] args) {

        SortSinglyListOfZerosOnesTwos list = new SortSinglyListOfZerosOnesTwos();
        list.insertNodeAtEndOfSinglyList(1);
        list.insertNodeAtEndOfSinglyList(1);
        list.insertNodeAtEndOfSinglyList(2);
        list.insertNodeAtEndOfSinglyList(0);
        list.insertNodeAtEndOfSinglyList(2);
        list.insertNodeAtEndOfSinglyList(0);
        list.insertNodeAtEndOfSinglyList(1);

        System.out.println("Unsorted singly linked list");
        list.displayContents(list.getHead());

        list.sortSinglyLinkedList(list.getHead());

        System.out.println("\nSorted singly linked list");
        list.displayContents(list.getHead());

        System.out.println("\nTime complexity is O(N) where N is the number of nodes in the singly linked list");

    }

    private void sortSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list of 0's, 1's and 2's is empty.");
        } else {
            int zeroCount = 0;
            int oneCount = 0;
            int twoCount = 0;

            //Get the count for 0's, 1's and 2's
            Node currentNode = headReference;
            while (currentNode != null) {
                if (currentNode.getData() == 2) {
                    twoCount++;
                } else if (currentNode.getData() == 1) {
                    oneCount++;
                } else if (currentNode.getData() == 0) {
                    zeroCount++;
                }
                currentNode = currentNode.getNext();
            }

            //Set zeroCount of nodes to 0
            currentNode = headReference;
            int count = 0;
            while (count < zeroCount) {
                currentNode.setData(0);
                currentNode = currentNode.getNext();
                count++;
            }

            //Set oneCount of node2 to 1
            count = 0;
            while (count < oneCount) {
                currentNode.setData(1);
                currentNode = currentNode.getNext();
                count++;
            }

            //Set twoCount of nodes to 2
            count = 0;
            while (count < twoCount) {
                currentNode.setData(2);
                currentNode = currentNode.getNext();
                count++;
            }
        }
    }

    private void insertNodeAtEndOfSinglyList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversalNode = head;
            while (traversalNode.getNext() != null)
                traversalNode = traversalNode.getNext();
            traversalNode.setNext(newNode);
        }
    }

    public void displayContents(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
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
