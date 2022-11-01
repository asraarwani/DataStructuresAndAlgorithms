package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 01-11-2022 11:47 am
 * <p>
 * Reference : https://www.geeksforgeeks.org/linked-list-in-zig-zag-fashion/
 */
public class RearrangeListInZigZagFashion {

    private Node head;

    public static void main(String[] args) {

        RearrangeListInZigZagFashion list = new RearrangeListInZigZagFashion();
        list.insertNodeIntoSLLAtEnd(1);
        list.insertNodeIntoSLLAtEnd(2);
        list.insertNodeIntoSLLAtEnd(3);
        list.insertNodeIntoSLLAtEnd(4);
        list.insertNodeIntoSLLAtEnd(5);
        list.insertNodeIntoSLLAtEnd(6);
        list.insertNodeIntoSLLAtEnd(7);
        list.insertNodeIntoSLLAtEnd(8);
        list.insertNodeIntoSLLAtEnd(9);
        list.insertNodeIntoSLLAtEnd(10);
        list.displayContents(list.getHead());

        System.out.println();
        list.rearrangeSinglyLinkedList(list.getHead());
        list.displayContents(list.getHead());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in the list");
    }

    private void rearrangeSinglyLinkedList(Node headReference) {
        Node currentNode = headReference;
        boolean flag = true;
        while (currentNode != null && currentNode.getNext() != null) {
            if (flag) {
                if (currentNode.getData() > currentNode.getNext().getData()) {
                    int temp = currentNode.getData();
                    currentNode.setData(currentNode.getNext().getData());
                    currentNode.getNext().setData(temp);
                }
                flag = false;
            } else {
                if (currentNode.getData() < currentNode.getNext().getData()) {
                    int temp = currentNode.getData();
                    currentNode.setData(currentNode.getNext().getData());
                    currentNode.getNext().setData(temp);
                }
                flag = true;
            }
            currentNode = currentNode.getNext();
        }
    }

    private void insertNodeIntoSLLAtEnd(int data) {
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

    private void displayContents(Node headReference) {
        if (headReference == null) {
            System.out.println("SLL is empty");
        } else {
            Node currentNode = headReference;
            while (currentNode != null) {
                System.out.print(currentNode.getData() + " ");
                currentNode = currentNode.getNext();
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
