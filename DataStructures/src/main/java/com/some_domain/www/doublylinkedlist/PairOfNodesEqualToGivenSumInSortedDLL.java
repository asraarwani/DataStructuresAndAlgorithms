package com.some_domain.www.doublylinkedlist;

/**
 * @author : asraar
 * @date : 20-10-2022 03:01 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-pairs-given-sum-doubly-linked-list/
 */
public class PairOfNodesEqualToGivenSumInSortedDLL {

    private Node head;
    private Node tail;

    public static void main(String[] args) {

        PairOfNodesEqualToGivenSumInSortedDLL list = new PairOfNodesEqualToGivenSumInSortedDLL();
        list.insertNodeInSortedDLL(5);
        list.insertNodeInSortedDLL(6);
        list.insertNodeInSortedDLL(2);
        list.insertNodeInSortedDLL(4);
        list.insertNodeInSortedDLL(7);
        list.insertNodeInSortedDLL(1);
        list.insertNodeInSortedDLL(3);
        list.insertNodeInSortedDLL(9);
        list.insertNodeInSortedDLL(10);
        list.insertNodeInSortedDLL(8);

        list.forwardTraversal(list.getHead());
        System.out.println();
        list.backwardTraversal(list.getTail());

        System.out.println();
        int givenSum = 11;
        System.out.println("Pairs of numbers whose sum is equal to " + givenSum + ". Time complexity is O(N)");
        list.findPairWithGivenSum(list.getHead(), list.getTail(), givenSum);
    }

    private void findPairWithGivenSum(Node headReference, Node tailReference, int givenSum) {
        while (headReference != null && tailReference != null) {
            if (headReference.getData() + tailReference.getData() == givenSum) {
                System.out.println(headReference.getData() + " and " + tailReference.getData());
                headReference = headReference.getNext();
                tailReference = tailReference.getPrevious();
            } else if (headReference.getData() + tailReference.getData() > givenSum) {
                tailReference = tailReference.getPrevious();
            } else {
                headReference = headReference.getNext();
            }
            if (headReference == tailReference || tailReference.getNext() == headReference) {
                break;
            }
        }
    }

    private void insertNodeInSortedDLL(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
        } else if (newNode.getData() < head.getData()) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            Node firstNode = head;
            Node secondNode = head.getNext();
            boolean inserted = false;
            while (firstNode != null && secondNode != null && !inserted) {
                if (newNode.getData() > firstNode.getData() && newNode.getData() <= secondNode.getData()) {
                    firstNode.setNext(newNode);
                    newNode.setPrevious(firstNode);
                    newNode.setNext(secondNode);
                    secondNode.setPrevious(newNode);
                    inserted = true;
                }
                firstNode = firstNode.getNext();
                secondNode = secondNode.getNext();
            }

            if (!inserted) {
                firstNode.setNext(newNode);
                newNode.setPrevious(firstNode);
                tail = newNode;
            }
        }
    }

    private void forwardTraversal(Node headReference) {
        if (headReference != null) {
            do {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            } while (headReference != null);
        }
    }

    private void backwardTraversal(Node tailReference) {
        if (tailReference != null) {
            do {
                System.out.print(tailReference.getData() + " ");
                tailReference = tailReference.getPrevious();
            } while (tailReference != null);
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    private class Node {
        private int data;
        private Node next;
        private Node previous;

        public Node(int data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }
}
