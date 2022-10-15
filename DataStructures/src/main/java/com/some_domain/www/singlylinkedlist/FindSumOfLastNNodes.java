package com.some_domain.www.singlylinkedlist;

import java.util.Stack;

/**
 * @author : asraar
 * @date : 15-10-2022 09:57 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-sum-last-n-nodes-given-linked-list/
 */
public class FindSumOfLastNNodes {

    private Node head;

    public static void main(String[] args) {

        FindSumOfLastNNodes list = new FindSumOfLastNNodes();
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

        int nNodes = 4;
        list.findSumOfLastNNodes(list.getHead(), nNodes);

        System.out.println();
        list.findSumOfLastNNodesUsingStack(list.getHead(), nNodes);
    }

    private void findSumOfLastNNodes(Node headReference, int nNodes) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            int nodeCount = getNodeCount(headReference);
            int count = 0;
            int countToSkip = nodeCount - nNodes;
            int sumOfLastNNodes = 0;
            Node traversalNode = headReference;
            while (traversalNode != null) {
                if (count < countToSkip) {
                    count++;
                } else {
                    sumOfLastNNodes += traversalNode.getData();
                }
                traversalNode = traversalNode.getNext();
            }
            System.out.println("\nSum of last " + nNodes + " nodes is " + sumOfLastNNodes);
            System.out.println("Time complexity is O(N)");
        }
    }

    private void findSumOfLastNNodesUsingStack(Node headReference, int nNodes) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
        } else {
            Stack<Node> stack = new Stack<>();
            int sumOfLastNNodes = 0;
            Node traversalNode = headReference;
            while (traversalNode != null) {
                stack.push(traversalNode);
                traversalNode = traversalNode.getNext();
            }
            int i = 0;
            while (i < nNodes) {
                sumOfLastNNodes += stack.pop().getData();
                i++;
            }
            System.out.println("\nSum of last " + nNodes + " nodes is " + sumOfLastNNodes);
            System.out.println("Time and space complexity is O(N)");
        }
    }


    private int getNodeCount(Node headReference) {
        if (headReference == null)
            return 0;
        else {
            return 1 + getNodeCount(headReference.getNext());
        }
    }

    public void insertNodeAtEndOfSinglyLinkedList(int data) {
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
