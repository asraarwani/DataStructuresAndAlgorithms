package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 18-10-2022 10:20 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/how-to-sort-a-linked-list-that-is-sorted-alternating-ascending-and-descending-orders/
 */
public class SortSinglyLinkedListSortedInAltAscDesc {

    private Node head;

    public static void main(String[] args) {

        //10 -> 40 -> 53 -> 30 -> 67 -> 12 -> 89
        //10 - 53 - 67 - 89
        //40 - 30 - 12
        SortSinglyLinkedListSortedInAltAscDesc list = new SortSinglyLinkedListSortedInAltAscDesc();
        list.insertNodeAtEndOfSinglyList(10);
        list.insertNodeAtEndOfSinglyList(40);
        list.insertNodeAtEndOfSinglyList(53);
        list.insertNodeAtEndOfSinglyList(30);
        list.insertNodeAtEndOfSinglyList(67);
        list.insertNodeAtEndOfSinglyList(12);
        list.insertNodeAtEndOfSinglyList(89);

        System.out.println("Before");
        list.displayContents(list.getHead());

        System.out.println("\nAfter");
        Node mergedListHeadNode = list.sortSinglyLinkedListSortedInAlternatingAscDesc(list.getHead());
        list.setHead(mergedListHeadNode);
        list.displayContents(list.getHead());
        System.out.println("\nTime complexity is O(N)");
    }

    /*
        Approach:
            Separate two lists.
            Reverse the one with descending order
            Merge both lists
     */
    private Node sortSinglyLinkedListSortedInAlternatingAscDesc(Node headReference) {

        //Split into two lists
        Node firstListHead = new Node(Integer.MIN_VALUE, null);
        Node secondListHead = new Node(Integer.MIN_VALUE, null);
        splitTwoLists(headReference, firstListHead, secondListHead);

        //Skip dummy nodes
        firstListHead = firstListHead.getNext();
        secondListHead = secondListHead.getNext();

        //Reverse the list sorted in descending order
        Node newHead = reverseSinglyLinkedList(secondListHead);

        //Merge the two sorted list (Both lists are now in ascending order)
        return mergeTwoSortedSinglyLinkedLists(firstListHead, newHead);
    }

    private void splitTwoLists(Node headReference, Node firstListHead, Node secondListHead) {
        Node traversalNode = headReference;
        while (traversalNode != null) {
            Node alternateNode = traversalNode.getNext();
            firstListHead.setNext(traversalNode);
            traversalNode.setNext(null);

            secondListHead.setNext(alternateNode);
            if (alternateNode != null) { // Null check needed if we have odd numbers of nodes in the list
                traversalNode = alternateNode.getNext(); // Moving to the next pair of nodes like 3rd , 5th and so on
                alternateNode.setNext(null);
            } else {
                traversalNode = null;
            }

            firstListHead = firstListHead.getNext();
            secondListHead = secondListHead.getNext();
        }
    }

    private Node reverseSinglyLinkedList(Node headReference) {
        if (headReference == null)
            return null;
        else {
            Node currentNode = headReference;
            Node nextNode = null;
            Node previousNode = null;
            while (currentNode != null) {
                nextNode = currentNode.getNext();
                currentNode.setNext(previousNode);
                previousNode = currentNode;
                currentNode = nextNode;
            }
            return previousNode;
        }
    }

    private Node mergeTwoSortedSinglyLinkedLists(Node firstListHead, Node secondListHead) {
        Node temporaryHead = new Node(Integer.MIN_VALUE, null);
        Node temporaryHeadRef = temporaryHead;
        while (firstListHead != null && secondListHead != null) {
            if (firstListHead.getData() <= secondListHead.getData()) {
                temporaryHead.setNext(firstListHead);
                firstListHead = firstListHead.getNext();
            } else {
                temporaryHead.setNext(secondListHead);
                secondListHead = secondListHead.getNext();
            }
            temporaryHead = temporaryHead.getNext();
        }

        while (firstListHead != null) {
            temporaryHead.setNext(firstListHead);
            firstListHead = firstListHead.getNext();
            temporaryHead = temporaryHead.getNext();
        }

        while (secondListHead != null) {
            temporaryHead.setNext(secondListHead);
            secondListHead = secondListHead.getNext();
            temporaryHead = temporaryHead.getNext();
        }
        return temporaryHeadRef.getNext();
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
