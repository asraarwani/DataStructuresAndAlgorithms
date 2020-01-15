package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 1/15/2020 1:08 PM
 * This class demonstrates how to insert nodes of second singly linked list into first singly linked at alternate positions
 */
//Reference : https://www.geeksforgeeks.org/merge-a-linked-list-into-another-linked-list-at-alternate-positions/
public class InsertNodesOfSecondSLLAtAlternatePositions {

    private Node head;
    private int size;

    public InsertNodesOfSecondSLLAtAlternatePositions() {
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

        InsertNodesOfSecondSLLAtAlternatePositions firstList = new InsertNodesOfSecondSLLAtAlternatePositions();
        firstList.insertNodeAtEndOfSinglyLinkedList(1);
        firstList.insertNodeAtEndOfSinglyLinkedList(3);
        firstList.insertNodeAtEndOfSinglyLinkedList(5);
        firstList.insertNodeAtEndOfSinglyLinkedList(7);
        firstList.insertNodeAtEndOfSinglyLinkedList(9);
        firstList.displayContentsOfSinglyLinkedList(firstList.getHead());

        System.out.println();

        InsertNodesOfSecondSLLAtAlternatePositions secondList = new InsertNodesOfSecondSLLAtAlternatePositions();
        secondList.insertNodeAtEndOfSinglyLinkedList(2);
        secondList.insertNodeAtEndOfSinglyLinkedList(4);
        secondList.insertNodeAtEndOfSinglyLinkedList(6);
        secondList.insertNodeAtEndOfSinglyLinkedList(8);
        secondList.insertNodeAtEndOfSinglyLinkedList(10);
        secondList.insertNodeAtEndOfSinglyLinkedList(20);
        secondList.insertNodeAtEndOfSinglyLinkedList(30);
        secondList.insertNodeAtEndOfSinglyLinkedList(40);
        secondList.displayContentsOfSinglyLinkedList(secondList.getHead());


        InsertNodesOfSecondSLLAtAlternatePositions mergedList = new InsertNodesOfSecondSLLAtAlternatePositions();
        Node mergeListHeadNodeRef = mergedList.mergeTwoSinglyLinkedListsAlternatePositions(firstList.getHead(), secondList.getHead(), secondList);
        mergedList.setHead(mergeListHeadNodeRef);
        System.out.println("\nAfter nodes of second sinlgy linked list are inserted in first singly linked list at alternate positions");
        mergedList.displayContentsOfSinglyLinkedList(mergedList.getHead());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in first singly linked list");

        if (secondList.getHead() != null) {
            System.out.println("\nRemaining nodes in second singly linked list are given as follows");
            secondList.displayContentsOfSinglyLinkedList(secondList.getHead());
        }
    }

    /*
        3rd parameter is just to update the head of second singly linked list
     */
    public Node mergeTwoSinglyLinkedListsAlternatePositions(Node firstListHead, Node secondListHead, InsertNodesOfSecondSLLAtAlternatePositions secondList) {
        Node mergedListHeadReference = firstListHead;
        Node firstListNext = null;
        Node secondListNext = null;
        while (firstListHead != null && secondListHead != null) {
            firstListNext = firstListHead.getNext();
            secondListNext = secondListHead.getNext();

            secondListHead.setNext(firstListHead.getNext());
            firstListHead.setNext(secondListHead);

            firstListHead = firstListNext;
            secondListHead = secondListNext;
        }
        //Since second list is modified, we have to update the head
        secondList.setHead(secondListHead);

        return mergedListHeadReference;
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

    public void insertNodeAtEndOfSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversingNode = head;
            while (traversingNode.getNext() != null)
                traversingNode = traversingNode.getNext();
            traversingNode.setNext(newNode);
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
