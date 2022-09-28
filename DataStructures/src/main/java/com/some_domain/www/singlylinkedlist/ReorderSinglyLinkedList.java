package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 28-09-2022 02:53 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/
 */
public class ReorderSinglyLinkedList {

    private Node head;

    public static void main(String[] args) {

        ReorderSinglyLinkedList list = new ReorderSinglyLinkedList();
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

        System.out.println("\nAfter rearranging the singly linked list");
        Node newHead = list.reorderSinglyLinkedList(list.getHead());
        list.setHead(newHead);
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nTime complexity is O(N)");
    }

    public Node reorderSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
            return null;
        } else {
            //Find the middle node and then split the list into two halves
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            while (fastPointer != null && fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
            }

            Node middleNode = slowPointer;
            Node secondPartHead = middleNode.getNext();
            middleNode.setNext(null);

            //Reverse the second part
            Node secondPartReversedHead = reverseSinglyLinkedList(secondPartHead);

            //Merge two halves at alternate positions
            Node temporaryHead = new Node(Integer.MIN_VALUE, null);
            Node currentNode = temporaryHead;
            while (headReference != null || secondPartReversedHead != null) {

                if (headReference != null) {
                    currentNode.setNext(headReference);
                    headReference = headReference.getNext();
                    currentNode = currentNode.getNext();
                }

                if (secondPartReversedHead != null) {
                    currentNode.setNext(secondPartReversedHead);
                    secondPartReversedHead = secondPartReversedHead.getNext();
                    currentNode = currentNode.getNext();
                }
            }
            return temporaryHead.getNext();
        }
    }

    private Node reverseSinglyLinkedList(Node headReference) {
        if (headReference == null)
            return null;
        else {
            Node currentNode = headReference;
            Node previousNode = null;
            Node nextNode = null;
            while (currentNode != null) {
                nextNode = currentNode.getNext();
                currentNode.setNext(previousNode);
                previousNode = currentNode;
                currentNode = nextNode;
            }
            return previousNode;
        }
    }

    private void insertNodeAtEndOfSinglyLinkedList(int data) {
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
            Node traversalNode = headReference;
            do {
                System.out.print(traversalNode.getData() + " ");
                traversalNode = traversalNode.getNext();
            } while (traversalNode != null);
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
