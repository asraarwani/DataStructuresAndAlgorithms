package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 07-10-2022 04:02 pm
 * <p>
 * Reference  : https://www.geeksforgeeks.org/delete-nodes-which-have-a-greater-value-on-right-side/
 */
public class DeleteNodesWithGreaterValueOnRight {

    private Node head;

    public static void main(String[] args) {

        DeleteNodesWithGreaterValueOnRight list = new DeleteNodesWithGreaterValueOnRight();
        list.insertNodeAtEnd(12);
        list.insertNodeAtEnd(15);
        list.insertNodeAtEnd(10);
        list.insertNodeAtEnd(11);
        list.insertNodeAtEnd(5);
        list.insertNodeAtEnd(6);
        list.insertNodeAtEnd(2);
        list.insertNodeAtEnd(3);
        list.displayContentsOfSLL(list.getHead());

        System.out.println("\nAfter deleting nodes which have greater value on right. Time complexity is O(N)");
        list.deleteNodesWithGreaterValueOnRight(list.getHead());
        list.displayContentsOfSLL(list.getHead());
    }

    private void deleteNodesWithGreaterValueOnRight(Node headReference) {

        Node newHead = reverseSinglyLinkedList(headReference);

        Node maxNode = newHead;
        Node currentNode = newHead;
        while (currentNode != null && currentNode.getNext() != null) {
            if (currentNode.getNext().getData() < maxNode.getData()) {
                currentNode.setNext(currentNode.getNext().getNext());
            } else {
                currentNode = currentNode.getNext();
                maxNode = currentNode;
            }
        }

        newHead = reverseSinglyLinkedList(newHead);
        this.setHead(newHead);
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

    private void insertNodeAtEnd(int data) {
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

    public void displayContentsOfSLL(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
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
