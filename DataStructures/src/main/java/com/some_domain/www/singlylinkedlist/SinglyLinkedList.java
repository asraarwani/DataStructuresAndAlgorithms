package com.some_domain.www.singlylinkedlist;

/**
 * @author : waniasra
 * @date : 10/9/2019 3:35 PM
 * This class demonstrates the basic operations on a singly linked list
 */
public class SinglyLinkedList {

    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        SinglyLinkedList list = new SinglyLinkedList();

        //Inserting few nodes at the beginning of the list
        list.insertNodeAtBeginningOfSinglyLinkedList(10);
        list.insertNodeAtBeginningOfSinglyLinkedList(9);
        list.insertNodeAtBeginningOfSinglyLinkedList(8);
        list.insertNodeAtBeginningOfSinglyLinkedList(7);
        list.insertNodeAtBeginningOfSinglyLinkedList(6);
        System.out.println("After inserting few nodes at the beginning of the singly linked list");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of the singly linked list is " + list.getSize() + " Head node is " + list.getHead().getData());

        System.out.println("");
        //Inserting few nodes at the end of the list
        list.insertNodeAtEndOfSinglyLinkedList(11);
        list.insertNodeAtEndOfSinglyLinkedList(12);
        list.insertNodeAtEndOfSinglyLinkedList(13);
        list.insertNodeAtEndOfSinglyLinkedList(14);
        list.insertNodeAtEndOfSinglyLinkedList(15);
        System.out.println("After inserting few nodes at the end of the singly linked list");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of the singly linked list is " + list.getSize() + " Head node is " + list.getHead().getData());


        System.out.println();
        //Inserting few nodes at specific indices in the list
        list.insertNodeAtSpecificIndexOfSinglyLinkedList(1000, 1);
        list.insertNodeAtSpecificIndexOfSinglyLinkedList(1000, 11);
        list.insertNodeAtSpecificIndexOfSinglyLinkedList(5000, 5);
        System.out.println("After inserting few nodes at specific indices in the singly linked list");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of the singly linked list is " + list.getSize() + " Head node is " + list.getHead().getData());

        System.out.println();
        //Deleting few nodes from the beginning of the singly linked list
        list.deleteNodeFromBeginningOfSinglyLinkedList();
        list.deleteNodeFromBeginningOfSinglyLinkedList();
        list.deleteNodeFromBeginningOfSinglyLinkedList();
        System.out.println("After deleting few nodes form the beginning of the singly linked list;");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of the singly linked list is " + list.getSize() + " Head node is " + list.getHead().getData());


        System.out.println();
        //Deleting few nodes from the end of the singly linked list
        list.deleteNodeFromEndOfSinglyLinkedList();
        list.deleteNodeFromEndOfSinglyLinkedList();
        list.deleteNodeFromEndOfSinglyLinkedList();
        System.out.println("After deleting few nodes form the end of the singly linked list;");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of the singly linked list is " + list.getSize() + " Head node is " + list.getHead().getData());

        System.out.println();
        //Deleting few nodes from the specific indices of the singly linked list
        list.deleteNodeFromSpecificIndexOfSinglyLinkedList(3);
        list.deleteNodeFromSpecificIndexOfSinglyLinkedList(1);
        list.deleteNodeFromSpecificIndexOfSinglyLinkedList(5);
        System.out.println("After deleting few nodes form the specific indices of the singly linked list;");
        list.displayContentsOfSinglyLinkedList(list.getHead());
        System.out.println("\nCurrent size of the singly linked list is " + list.getSize() + " Head node is " + list.getHead().getData());


        //Searching for a given node in the singly linked list
        int givenNode = 5000;
        boolean isPresent = list.searchForGivenNode(givenNode);
        if (isPresent)
            System.out.println(givenNode + " is present in the singly linked list");
        else
            System.out.println(givenNode + " is NOT present in the singly linked list");

    }

    public boolean searchForGivenNode(int key) {
        if (head == null) {
            System.out.println("Singly linked list is empty.");
            return false;
        } else {
            Node traversingNode = head;
            while (traversingNode != null) {
                if (traversingNode.getData() == key) {
                    return true;
                }
                traversingNode = traversingNode.getNext();
            }
            return false;
        }
    }

    public void deleteNodeFromSpecificIndexOfSinglyLinkedList(int deletionIndex) {
        if (deletionIndex < 1 || deletionIndex > size) {
            System.out.println("Deletion form the index " + deletionIndex + " is not possible.");
        } else if (deletionIndex == 1) {
            deleteNodeFromBeginningOfSinglyLinkedList();
        } else if (deletionIndex == size) {
            deleteNodeFromEndOfSinglyLinkedList();
        } else {
            Node traversingNode = head;
            for (int i = 0; i < deletionIndex - 1; i++) {
                traversingNode = traversingNode.getNext();
            }
            Node deletedNode = traversingNode.getNext();
            if (deletedNode.getNext() != null) {
                traversingNode.setNext(deletedNode.getNext()); // In case there are  nodes after the node to be deleted
            } else {
                traversingNode.setNext(null);                  //In case there are no further nodes after the node to be deleted
            }
            size--;
            System.out.println("Node deleted form index " + deletionIndex + " is " + deletedNode.getData());
        }
    }

    public void deleteNodeFromBeginningOfSinglyLinkedList() {
        if (head == null) {
            System.out.println("Singly linked list is empty");
        } else {
            Node deletedNode = head;
            head = head.getNext();
            System.out.println("Node deleted from the beginning of the singly linked list is " + deletedNode.getData());
            size--;
        }
    }

    public void deleteNodeFromEndOfSinglyLinkedList() {
        if (head == null) {
            System.out.println("Singly linked list is empty");
        } else {
            Node traversingNode = head;
            Node previousNode = null;
            while (traversingNode.getNext() != null) {
                previousNode = traversingNode;
                traversingNode = traversingNode.getNext();
            }
            previousNode.setNext(null);
            System.out.println("Node deleted from the end of the singly linked list is " + traversingNode.getData());
            size--;
        }
    }

    public void displayContentsOfSinglyLinkedList(Node headReference) {
        if (headReference == null) {
            System.out.println("Singly linked list is empty.");
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void insertNodeAtBeginningOfSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void insertNodeAtEndOfSinglyLinkedList(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node traversingNode = head;
            while (traversingNode.getNext() != null)
                traversingNode = traversingNode.getNext();
            traversingNode.setNext(newNode);
        }
        size++;
    }

    public void insertNodeAtSpecificIndexOfSinglyLinkedList(int data, int insertionIndex) {
        Node newNode = new Node(data, null);
        if (insertionIndex < 1 || insertionIndex > size) {
            System.out.println("Insertion at this index " + insertionIndex + " is not possible");
        } else if (insertionIndex == 1) {
            insertNodeAtBeginningOfSinglyLinkedList(data);
        } else if (insertionIndex == size) {
            insertNodeAtEndOfSinglyLinkedList(data);
        } else {
            Node traversingNode = head;
            for (int i = 0; i < insertionIndex - 1; i++) {
                traversingNode = traversingNode.getNext();
            }
            newNode.setNext(traversingNode.getNext());
            traversingNode.setNext(newNode);
            size++;
        }
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
