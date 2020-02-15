package com.some_domain.www.doublylinkedlist;

/**
 * @author : waniasra
 * @date : 2/14/2020 6:16 PM
 * This class demonstrates how to swap k-th node from beginning with k-th node from end in a doubly linked list
 */
public class SwapKthNodeFromBegWIthKthNodeFromEnd {

    private Node head;
    private Node tail;

    public SwapKthNodeFromBegWIthKthNodeFromEnd() {
        this.head = null;
        this.tail = null;
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

    public static void main(String[] args) {

        SwapKthNodeFromBegWIthKthNodeFromEnd list = new SwapKthNodeFromBegWIthKthNodeFromEnd();

        list.insertNodeIntoDoublyLinkedListAtEnd(1);
        list.insertNodeIntoDoublyLinkedListAtEnd(2);
        list.insertNodeIntoDoublyLinkedListAtEnd(3);
        list.insertNodeIntoDoublyLinkedListAtEnd(4);
        list.insertNodeIntoDoublyLinkedListAtEnd(5);
        list.insertNodeIntoDoublyLinkedListAtEnd(6);
        list.insertNodeIntoDoublyLinkedListAtEnd(7);
        list.insertNodeIntoDoublyLinkedListAtEnd(8);
        list.insertNodeIntoDoublyLinkedListAtEnd(9);
        list.insertNodeIntoDoublyLinkedListAtEnd(10);
        list.insertNodeIntoDoublyLinkedListAtEnd(11);


        System.out.println("Forward traversal");
        list.displayContentsOfDoublyLinkedListUsingForwardTraversal(list.getHead());

        System.out.println("\nBackward traversal");
        list.displayContentsOfDoublyLinkedListUsingBackwardTraversal(list.getTail());

        System.out.println("\n\n\nAfter swapping k-th node from beginning");
        int k = 3;
        list.replaceKthNodeFromBeginningWIthKthNodeFromEnd(list.getHead(), list.getTail(), k);

        System.out.println("Forward traversal");
        list.displayContentsOfDoublyLinkedListUsingForwardTraversal(list.getHead());

        System.out.println("\nBackward traversal");
        list.displayContentsOfDoublyLinkedListUsingBackwardTraversal(list.getTail());
    }

    public void replaceKthNodeFromBeginningWIthKthNodeFromEnd(Node headReference, Node tailReference, int k) {

        if (k == 1) {
            swapFirstAndLastNode(headReference, tailReference);
            return;
        }

        int nodeCount = getNodeCount(headReference);
        if (k == nodeCount) {
            swapFirstAndLastNode(headReference, tailReference);
            return;
        }

        if (2 * k - 1 == nodeCount) {
            return;
        }

        Node firstNode = headReference;
        for (int i = 1; i < k; i++) {
            firstNode = firstNode.getNext();
        }
        Node firstNodePrevious = firstNode.getPrevious();
        Node firstNodeNext = firstNode.getNext();


        Node secondNode = tailReference;
        for (int i = 1; i < k; i++) {
            secondNode = secondNode.getPrevious();
        }
        Node secondNodePrevious = secondNode.getPrevious();
        Node secondNodeNext = secondNode.getNext();


        if (firstNodePrevious != null && secondNode != null) {
            firstNodePrevious.setNext(secondNode);
            secondNode.setPrevious(firstNodePrevious);
            secondNode.setNext(firstNodeNext);
            firstNodeNext.setPrevious(secondNode);
        }

        if (secondNodePrevious != null && secondNodeNext != null) {
            secondNodeNext.setPrevious(firstNode);
            firstNode.setNext(secondNodeNext);
            secondNodePrevious.setNext(firstNode);
            firstNode.setPrevious(secondNodePrevious);
        }
    }

    private void swapFirstAndLastNode(Node headReference, Node tailReference) {
        Node headRef = headReference;
        Node tailRef = tailReference;

        headReference = headReference.getNext();
        tailReference = tailReference.getPrevious();

        tailReference.setNext(headRef);
        headRef.setPrevious(tailReference);
        headRef.setNext(null);
        this.setTail(tailReference.getNext());

        headReference.setPrevious(tailRef);
        tailRef.setNext(headReference);
        tailRef.setPrevious(null);
        this.setHead(headReference.getPrevious());
    }

    private int getNodeCount(Node headReference) {
        int nodeCount = 0;
        while (headReference != null) {
            nodeCount++;
            headReference = headReference.getNext();
        }
        return nodeCount;
    }


    public void displayContentsOfDoublyLinkedListUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Doubly linked list ie empty");
            return;
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void displayContentsOfDoublyLinkedListUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Doubly linked list ie empty");
            return;
        } else {
            while (tailReference != null) {
                System.out.print(tailReference.getData() + " ");
                tailReference = tailReference.getPrevious();
            }
        }
    }

    public void insertNodeIntoDoublyLinkedListAtEnd(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
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
