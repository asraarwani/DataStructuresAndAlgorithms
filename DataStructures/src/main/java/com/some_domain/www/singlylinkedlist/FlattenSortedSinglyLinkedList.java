package com.some_domain.www.singlylinkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author : asraar
 * @date : 31-10-2022 03:44 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/flattening-a-linked-list/
 */
public class FlattenSortedSinglyLinkedList {

    private Node head;

    public static void main(String[] args) {

        FlattenSortedSinglyLinkedList firstDownList = new FlattenSortedSinglyLinkedList();
        firstDownList.insertNodeInDownwardOrder(7);
        firstDownList.insertNodeInDownwardOrder(5);
        firstDownList.insertNodeInDownwardOrder(30);
        firstDownList.insertNodeInDownwardOrder(8);

        FlattenSortedSinglyLinkedList secondDownList = new FlattenSortedSinglyLinkedList();
        secondDownList.insertNodeInDownwardOrder(20);
        secondDownList.insertNodeInDownwardOrder(10);

        FlattenSortedSinglyLinkedList thirdDownList = new FlattenSortedSinglyLinkedList();
        thirdDownList.insertNodeInDownwardOrder(50);
        thirdDownList.insertNodeInDownwardOrder(22);
        thirdDownList.insertNodeInDownwardOrder(19);

        FlattenSortedSinglyLinkedList fourthDownList = new FlattenSortedSinglyLinkedList();
        fourthDownList.insertNodeInDownwardOrder(28);
        fourthDownList.insertNodeInDownwardOrder(40);
        fourthDownList.insertNodeInDownwardOrder(45);
        fourthDownList.insertNodeInDownwardOrder(35);


        FlattenSortedSinglyLinkedList mainList = new FlattenSortedSinglyLinkedList();
        mainList.mergeAllDownLists(mainList, firstDownList.getHead(), secondDownList.getHead(), thirdDownList.getHead(), fourthDownList.getHead());
        mainList.displayContentsInVerticalOrder(mainList.getHead());

        System.out.println();
        mainList.flatten(mainList.getHead());
        System.out.println("\nTime complexity is O(N * M * log(N)) where N is the number of nodes in the main list and M is the number of nodes in down lists");
        System.out.println("Space complexity is O(N)");
    }

    private void flatten(Node headNode) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new NodeComparator());
        Node currentNode = headNode;
        while (currentNode != null) {
            minHeap.add(currentNode);
            currentNode = currentNode.getNext();
        }

        while (!minHeap.isEmpty()) {
            Node poppedNode = minHeap.poll();
            System.out.print(poppedNode.getData() + " ");

            if (poppedNode.getDown() != null) { //if the popped node has a down node, add that to minHeap
                minHeap.add(poppedNode.getDown());
            }
        }
    }

    private class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node firstNode, Node secondNode) {
            if (firstNode.getData() > secondNode.getData())
                return 1;
            else if (firstNode.getData() < secondNode.getData())
                return -1;
            return 0;
        }
    }

    private void mergeAllDownLists(FlattenSortedSinglyLinkedList mainList, Node firstDownListHead, Node secondDownListHead, Node thirdDownListHead, Node fourthDownListHead) {
        firstDownListHead.setNext(secondDownListHead);
        secondDownListHead.setNext(thirdDownListHead);
        thirdDownListHead.setNext(fourthDownListHead);
        fourthDownListHead.setNext(null);
        mainList.setHead(firstDownListHead);
    }

    private void insertNodeInDownwardOrder(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = newNode;
        } else if (newNode.getData() < head.getData()) {
            newNode.setDown(head);
            head = newNode;
        } else {
            Node firstNode = head;
            Node secondNode = head.getDown();
            boolean inserted = false;
            while (firstNode != null && secondNode != null && !inserted) {

                if (newNode.getData() > firstNode.getData() && newNode.getData() <= secondNode.getData()) {
                    firstNode.setDown(newNode);
                    newNode.setDown(secondNode);
                    inserted = true;
                }
                firstNode = firstNode.getDown();
                secondNode = secondNode.getDown();
            }
            if (!inserted) {
                firstNode.setDown(newNode);
            }
        }
    }

    public void displayContentsInVerticalOrder(Node headReference) {
        if (headReference == null) {
            System.out.println("SLL with down link is empty");
        } else {
            Node traversalNode = headReference;
            while (traversalNode != null) {
                System.out.print("Head : " + traversalNode.getData() + "->");
                if (traversalNode.getDown() != null) {
                    Node currentDown = traversalNode.getDown();
                    while (currentDown != null) {
                        System.out.print(currentDown.getData() + " ");
                        currentDown = currentDown.getDown();
                    }
                }
                traversalNode = traversalNode.getNext();
                System.out.println();
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
        private Node down;

        public Node(int data, Node next, Node down) {
            this.data = data;
            this.next = next;
            this.down = down;
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

        public Node getDown() {
            return down;
        }

        public void setDown(Node down) {
            this.down = down;
        }
    }
}
