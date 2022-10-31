package com.some_domain.www.singlylinkedlist;

/**
 * @author : asraar
 * @date : 31-10-2022 09:41 pm
 * <p>
 * Reference: https://www.geeksforgeeks.org/delete-last-occurrence-of-an-item-from-linked-list/
 */
public class DeleteLastOccurrenceOfNode {

    private Node head;

    public static void main(String[] args) {

        DeleteLastOccurrenceOfNode list = new DeleteLastOccurrenceOfNode();
        list.insertNodeAtEndIntoSLL(1);
        list.insertNodeAtEndIntoSLL(1);
        list.insertNodeAtEndIntoSLL(2);
        list.insertNodeAtEndIntoSLL(4);
        list.insertNodeAtEndIntoSLL(5);
        list.insertNodeAtEndIntoSLL(2);
        list.insertNodeAtEndIntoSLL(7);
        list.insertNodeAtEndIntoSLL(10);
        list.insertNodeAtEndIntoSLL(9);
        list.insertNodeAtEndIntoSLL(10);
        list.displayContentsOfSLL(list.getHead());
        System.out.println();

        int key = 1;
        list.deleteLastOccurrenceOfKey(list.getHead(), key);
        list.displayContentsOfSLL(list.getHead());
    }

    private void deleteLastOccurrenceOfKey(Node headReference, int key) {
        if (headReference == null)
            System.out.println("SLL is empty");
        else {
            Node currentNode = headReference;
            Node targetNode = null;
            while (currentNode != null) {
                if (currentNode.getData() == key) {
                    targetNode = currentNode;
                }
                currentNode = currentNode.getNext();
            }


            //If last node occurrence is the last node in the list
            if (targetNode != null && targetNode.getNext() == null) {
                currentNode = headReference;
                while (currentNode != null && currentNode.getNext() != targetNode) {
                    currentNode = currentNode.getNext();
                }
                currentNode.setNext(null);
            }

           /* //If last node occurrence is NOT the last node in the list
            if (targetNode != null && targetNode.getNext() != null) {
                targetNode.setData(targetNode.getNext().getData()); //Deleting diff node but using deleted node data
                targetNode.setNext(targetNode.getNext().getNext());
            }*/

            //If last node occurrence is NOT the last node in the list
            if (targetNode != null && targetNode.getNext() != null) {
                currentNode = headReference;
                Node previousNode = null;
                while (currentNode != null && currentNode != targetNode) {
                    previousNode = currentNode;
                    currentNode = currentNode.getNext();
                }
                previousNode.setNext(currentNode.getNext());
            }
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
