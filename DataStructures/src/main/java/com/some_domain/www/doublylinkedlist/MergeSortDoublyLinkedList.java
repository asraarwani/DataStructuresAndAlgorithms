package com.some_domain.www.doublylinkedlist;

/**
 * @author : waniasra
 * @date : 1/14/2020 9:46 PM
 * This class demonstrates how to do a merge sort on a doubly linked list
 */
public class MergeSortDoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public MergeSortDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static void main(String[] args) {

        MergeSortDoublyLinkedList list = new MergeSortDoublyLinkedList();
        list.insertNodeIntoDoublyLinkedListAtBeginning(8);
        list.insertNodeIntoDoublyLinkedListAtBeginning(10);
        list.insertNodeIntoDoublyLinkedListAtBeginning(7);
        list.insertNodeIntoDoublyLinkedListAtBeginning(9);
        list.insertNodeIntoDoublyLinkedListAtBeginning(5);
        list.insertNodeIntoDoublyLinkedListAtBeginning(6);
        list.insertNodeIntoDoublyLinkedListAtBeginning(3);
        list.insertNodeIntoDoublyLinkedListAtBeginning(4);
        list.insertNodeIntoDoublyLinkedListAtBeginning(1);
        list.insertNodeIntoDoublyLinkedListAtBeginning(2);

        System.out.println("Before sorting");
        list.displayContentsUsingForwardTraversal(list.getHead());
        System.out.println();
        list.displayContentsUsingBackwardTraversal(list.getTail());
        System.out.println("\nHead : " + list.getHead().getData() + " , Tail : " + list.getTail().getData());

        System.out.println("\nAfter sorting");
        Node newHead = list.mergeSort(list.getHead());
        list.setHead(newHead);

        list.displayContentsUsingForwardTraversal(list.getHead());
        System.out.println();
        list.displayContentsUsingBackwardTraversal(list.getTail());
        System.out.println("\nHead : " + list.getHead().getData() + " , Tail : " + list.getTail().getData());
        System.out.println("\nTime complexity is O(NlogN)");
    }

    public Node mergeSort(Node headReference) {
        if (headReference == null || headReference.getNext() == null) {
            return headReference;
        } else {
            Node resultNode = null;
            Node middleNode = getMiddleNode(headReference);
            Node secondHalfHeadRef = middleNode.getNext();

            //Unset the forward and backward links
            secondHalfHeadRef.setPrevious(null);
            middleNode.setNext(null);

            Node firstPartHead = mergeSort(headReference);
            Node secondPartHead = mergeSort(secondHalfHeadRef);
            resultNode = merge(firstPartHead, secondPartHead);
            return resultNode;
        }
    }

    private Node merge(Node firstPartHead, Node secondPartHead) {
        Node resultNode = null;
        if (firstPartHead == null)
            resultNode = secondPartHead;
        else if (secondPartHead == null)
            resultNode = firstPartHead;
        else if (firstPartHead.getData() <= secondPartHead.getData()) {
            resultNode = firstPartHead;
            resultNode.setNext(merge(firstPartHead.getNext(), secondPartHead));
            resultNode.getNext().setPrevious(firstPartHead);
        } else {
            resultNode = secondPartHead;
            resultNode.setNext(merge(firstPartHead, secondPartHead.getNext()));
            resultNode.getNext().setPrevious(secondPartHead);

            //Setting the new tail node for doubly linked list
            Node temporaryNode = secondPartHead;
            while (temporaryNode.getNext() != null) {
                temporaryNode = temporaryNode.getNext();
            }
            this.setTail(temporaryNode);
        }
        return resultNode;
    }

    private Node getMiddleNode(Node headReference) {
        if (headReference == null || headReference.getNext() == null)
            return headReference;
        else {
            Node slowPointer = headReference;
            Node fastPointer = headReference.getNext();
            while (fastPointer != null && fastPointer.getNext() != null) {
                slowPointer = slowPointer.getNext();
                fastPointer = fastPointer.getNext().getNext();
            }
            return slowPointer;
        }
    }

    public void displayContentsUsingForwardTraversal(Node headReference) {
        if (headReference == null) {
            System.out.println("Doubly linked list is empty");
            return;
        } else {
            while (headReference != null) {
                System.out.print(headReference.getData() + " ");
                headReference = headReference.getNext();
            }
        }
    }

    public void displayContentsUsingBackwardTraversal(Node tailReference) {
        if (tailReference == null) {
            System.out.println("Doubly linked list is empty");
            return;
        } else {
            while (tailReference != null) {
                System.out.print(tailReference.getData() + " ");
                tailReference = tailReference.getPrevious();
            }
        }
    }

    public void insertNodeIntoDoublyLinkedListAtBeginning(int data) {
        Node newNode = new Node(data, null, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
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
