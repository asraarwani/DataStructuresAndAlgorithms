package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 31-10-2022 07:16 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/delete-middle-of-linked-list/
 */
public class DeleteMiddleNode {

    private Node head;


    public static void main(String[] args) {

        DeleteMiddleNode list = new DeleteMiddleNode();
        list.insertNodeAtEndIntoSLL(1);
        list.insertNodeAtEndIntoSLL(2);
        list.insertNodeAtEndIntoSLL(3);
        list.insertNodeAtEndIntoSLL(4);
        list.insertNodeAtEndIntoSLL(5);
        list.insertNodeAtEndIntoSLL(6);
        list.insertNodeAtEndIntoSLL(7);
        list.insertNodeAtEndIntoSLL(8);
        list.insertNodeAtEndIntoSLL(9);
        list.insertNodeAtEndIntoSLL(10);
        list.displayContentsOfSLL(list.getHead());
        System.out.println();

        list.deleteMiddleNodeUsingTwoPointers(list.getHead());

        list.displayContentsOfSLL(list.getHead());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in the SLL.");

        System.out.println();
        list.deleteMiddleNodeUsingNodeCount(list.getHead());
        list.displayContentsOfSLL(list.getHead());
        System.out.println("\nTime complexity is O(N) where N is the number of nodes in the SLL.");

    }

    //Using two pointers - single traversal
    private void deleteMiddleNodeUsingTwoPointers(Node headReference) {
        if (headReference == null) {
            System.out.println("SLL is empty");
        } else {
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            Node previousNode = null;
            while (fastPointer != null && fastPointer.getNext() != null) {
                previousNode = slowPointer;
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
            }
            System.out.println("\nDeleted node : " + slowPointer.getData());
            previousNode.setNext(slowPointer.getNext());
        }
    }

    //Using node count - list is traversed twice
    private void deleteMiddleNodeUsingNodeCount(Node headReference) {
        if (headReference == null) {
            System.out.println("SLL is empty");
        } else {
            int nodeCount = getNodeCount(headReference);
            int deletionIndex = nodeCount / 2;
            Node previousNode = null;
            Node currentNode = headReference;
            for (int i = 0; i < deletionIndex; i++) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            System.out.println("Deleted node : " + currentNode.getData());
            previousNode.setNext(currentNode.getNext());
        }
    }

    private int getNodeCount(Node headReference) {
        if (headReference == null)
            return 0;
        else
            return 1 + getNodeCount(headReference.getNext());
    }

    private void insertNodeAtEndIntoSLL(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null)
                currentNode = currentNode.getNext();
            currentNode.setNext(newNode);
        }
    }

    private void displayContentsOfSLL(Node headReference) {
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
