package com.some_domain.www.singlylinkedlist;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : waniasra
 * @date : 2/17/2020 5:25 PM
 * This class demonstrates how to reverse singly linked list in groups of of size K
 */
//Reference : https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
//Reference : https://www.geeksforgeeks.org/reverse-linked-list-groups-given-size-set-2/
//Reference : https://www.geeksforgeeks.org/reverse-a-singly-linked-list-in-groups-of-given-size-set-3/
public class ReverseSinglyLinkedListInGroupsOfK {

    private Node head;
    private int size;

    public ReverseSinglyLinkedListInGroupsOfK() {
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

        ReverseSinglyLinkedListInGroupsOfK list = new ReverseSinglyLinkedListInGroupsOfK();
        list.insertNodeIntoSinglyLinkedListAtEnd(1);
        list.insertNodeIntoSinglyLinkedListAtEnd(2);
        list.insertNodeIntoSinglyLinkedListAtEnd(3);
        list.insertNodeIntoSinglyLinkedListAtEnd(4);
        list.insertNodeIntoSinglyLinkedListAtEnd(5);
        list.insertNodeIntoSinglyLinkedListAtEnd(6);
        list.insertNodeIntoSinglyLinkedListAtEnd(7);
        list.insertNodeIntoSinglyLinkedListAtEnd(8);
        list.insertNodeIntoSinglyLinkedListAtEnd(9);
        list.insertNodeIntoSinglyLinkedListAtEnd(10);

        int k = 3;
        System.out.println("Before reversing the singly linked list in groups of " + k);
        list.displayContentsOfSinglyLinkedList(list.getHead());

        System.out.println("\nAfter reversing the singly linked list in groups of " + k);
        Node newHead = list.reverseSinglyLinkedListInGroupsOfK_Recursive(list.getHead(), k);
        list.setHead(newHead);
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nTime complexity is O(N)");

        newHead = list.reverseSinglyLinkedListInGroupsOfK_IterativeUsingStack(list.getHead(), k);
        list.setHead(newHead);
        System.out.println("\nAfter reversing the singly linked list in groups of " + k + " using iterative approach using Stack");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nTime and space complexity is O(N)");

        list.reverseSinglyLinkedListInGroupsOfK_UsingDeQueue(list.getHead(), k);
        System.out.println("\nAfter reversing the singly linked list in groups of " + k + " using iterative approach using Deque");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nTime and space complexity is O(N)");
    }

    //https://www.geeksforgeeks.org/reverse-linked-list-groups-given-size-set-2/
    public Node reverseSinglyLinkedListInGroupsOfK_IterativeUsingStack(Node headReference, int k) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return null;
        } else {
            Stack<Node> stack = new Stack<>();
            Node currentNode = headReference;
            Node previousNode = null;
            int count = 0;
            while (currentNode != null) {
                count = 0;
                while (currentNode != null && count < k) {
                    stack.push(currentNode);
                    currentNode = currentNode.getNext();
                    count++;
                }

                while (stack.size() > 0) {

                    if (previousNode == null) {
                        previousNode = stack.pop();
                        headReference = previousNode;
                    } else {
                        previousNode.setNext(stack.pop());
                        previousNode = previousNode.getNext();
                    }
                }
            }
            previousNode.setNext(null);
            return headReference;
        }
    }

    //https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
    public Node reverseSinglyLinkedListInGroupsOfK_Recursive(Node headReference, int k) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
            return null;
        } else {
            Node currentNode = headReference;
            Node previousNode = null;
            Node nextNode = null;
            int count = 0;
            while (count < k && currentNode != null) {
                nextNode = currentNode.getNext();
                currentNode.setNext(previousNode);
                previousNode = currentNode;
                currentNode = nextNode;
                count++;
            }
            if (nextNode != null) {
                headReference.setNext(reverseSinglyLinkedListInGroupsOfK_Recursive(nextNode, k));
            }
            return previousNode;
        }
    }

    //https://www.geeksforgeeks.org/reverse-a-singly-linked-list-in-groups-of-given-size-set-3/
    public Node reverseSinglyLinkedListInGroupsOfK_UsingDeQueue(Node headReference, int k) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty");
            return null;
        } else {
            Deque<Node> deque = new LinkedList<>();
            Node currentNode = headReference;
            int count = 0;
            while (currentNode != null) {

                count = 0;
                while (count < k) {
                    deque.push(currentNode);
                    if (currentNode == null) {
                        break;
                    }
                    currentNode = currentNode.getNext();
                    count++;
                }

                if (!deque.isEmpty()) {
                    Node frontPoppedNode = deque.pollFirst();
                    Node backPoppedNode = deque.pollLast();
                    //Swap the data of the two nodes
                    if (frontPoppedNode == null || backPoppedNode == null) {
                        break;
                    }
                    int temporaryVariable = frontPoppedNode.getData();
                    frontPoppedNode.setData(backPoppedNode.getData());
                    backPoppedNode.setData(temporaryVariable);

                    //If there is a node left in deque we flush the deque (If K is odd, we will end up having one node in deque)
                    if (deque.size() == 1) {
                        deque.clear();
                    }
                }
            }
            return headReference;
        }
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linekd list is empty");
            return;
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void insertNodeIntoSinglyLinkedListAtEnd(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
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
