package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 31-10-2022 07:26 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/delete-a-linked-list-node-at-a-given-position/
 */
public class DeleteNodeFromGivenIndex {

    private Node head;

    public static void main(String[] args) {

        DeleteNodeFromGivenIndex list = new DeleteNodeFromGivenIndex();
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

        int deletionIndex = 3;
        list.deleteNodeFromGivenIndex(list.getHead(), deletionIndex);
        System.out.println("Node deleted form index " + deletionIndex);
        list.displayContentsOfSLL(list.getHead());
        System.out.println("\nTime complexity is O(N)");

    }

    private void deleteNodeFromGivenIndex(Node headReference, int deletionIndex) {
        if (headReference == null) {
            System.out.println("SLl is empty");
        } else {

            //If deletion index is 0 , deleting head node
            if (deletionIndex == 0) {
                headReference = headReference.getNext();
                this.setHead(headReference);
                return;
            }

            Node currentNode = headReference;
            Node previousNode = null;
            int count = 0;
            while (currentNode != null && count < deletionIndex) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
                count++;
            }
            if (currentNode != null)
                previousNode.setNext(currentNode.getNext());
        }
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
