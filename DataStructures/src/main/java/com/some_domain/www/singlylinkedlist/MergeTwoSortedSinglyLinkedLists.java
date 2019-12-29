package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 12/28/2019 10:18 PM
 * This class demonstrates how to merge two sorted singly linked lists
 */
public class MergeTwoSortedSinglyLinkedLists {

    private Node head;
    private int size;

    public MergeTwoSortedSinglyLinkedLists() {
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

        MergeTwoSortedSinglyLinkedLists firstList = new MergeTwoSortedSinglyLinkedLists();
        firstList.insertNodeIntoSinglyLinkedListInOrder(3);
        firstList.insertNodeIntoSinglyLinkedListInOrder(1);
        firstList.insertNodeIntoSinglyLinkedListInOrder(7);
        firstList.insertNodeIntoSinglyLinkedListInOrder(5);
        firstList.insertNodeIntoSinglyLinkedListInOrder(9);
        System.out.println("First sorted singly linked list");
        firstList.printContentsOfSinglyLinkedList(firstList.getHead());

        MergeTwoSortedSinglyLinkedLists secondList = new MergeTwoSortedSinglyLinkedLists();
        secondList.insertNodeIntoSinglyLinkedListInOrder(2);
        secondList.insertNodeIntoSinglyLinkedListInOrder(6);
        secondList.insertNodeIntoSinglyLinkedListInOrder(4);
        secondList.insertNodeIntoSinglyLinkedListInOrder(10);
        secondList.insertNodeIntoSinglyLinkedListInOrder(8);
        System.out.println("\nSecond sorted singly linked list");
        secondList.printContentsOfSinglyLinkedList(secondList.getHead());

        MergeTwoSortedSinglyLinkedLists mergesList = new MergeTwoSortedSinglyLinkedLists();
        Node mergeListHeadNode = mergesList.mergeTwoSortedSinglyLinkedLists(firstList.getHead(), secondList.getHead());
        System.out.println("\nMerged sorted singly linked list");
        mergesList.printContentsOfSinglyLinkedList(mergeListHeadNode);

        System.out.println("\nTime complexity is O(M + N) where M and N are the number of nodes in first and second sorted singly linked list resp.");
    }

    public Node mergeTwoSortedSinglyLinkedLists(Node firstListHead, Node secondListHead) {
        Node tempNode = new Node(Integer.MIN_VALUE, null);
        Node tempNodeRef = tempNode;
        while (firstListHead != null && secondListHead != null) {
            if (firstListHead.getData() < secondListHead.getData()) {
                tempNode.setNext(firstListHead);
                firstListHead = firstListHead.getNext();
            } else {
                tempNode.setNext(secondListHead);
                secondListHead = secondListHead.getNext();
            }
            tempNode = tempNode.getNext();
        }
        //Appending rest of the first list in case second list exhausted and terminated the above loop
        if (firstListHead != null) {
            tempNode.setNext(firstListHead);
        }

        //Appending rest of the second list in case first list exhausted and terminated the above loop
        if (secondListHead != null) {
            tempNode.setNext(secondListHead);
        }
        return tempNodeRef.getNext();
    }

    public void insertNodeIntoSinglyLinkedListInOrder(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else if (data < head.getData()) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node currentNode = head;
            Node nextNode = head.getNext();
            boolean nodeInserted = false;
            while (currentNode != null && currentNode.getNext() != null) {
                if (newNode.getData() > currentNode.getData() && newNode.getData() < nextNode.getData()) {
                    currentNode.setNext(newNode);
                    newNode.setNext(nextNode);
                    nodeInserted = true;
                    break;
                } else {
                    currentNode = nextNode;
                    nextNode = nextNode.getNext();
                }
            }
            //In case node doesn't fit in between the nodes, we append it at the end
            if (!nodeInserted) {
                currentNode.setNext(newNode);
            }
        }
    }

    public void printContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Sorted singly linked list is empty");
            return;
        } else {
            Node traversalNode = headReference;
            while (traversalNode != null) {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getNext();
            }
        }
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
