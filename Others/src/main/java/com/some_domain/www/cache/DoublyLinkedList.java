package com.some_domain.www.cache;

public class DoublyLinkedList {

    private LinkedListNode head;
    private LinkedListNode tail;

    public boolean insertNodeAtFront(LinkedListNode newNode) {
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        return true;
    }

    public LinkedListNode removeNodeFromRear() {
        if (tail != null) {
            LinkedListNode removedNode = tail;
            tail = tail.getPrevious();
            tail.setNext(null);
            removedNode.setPrevious(null);
            return removedNode;
        } else {
            return null;
        }
    }

    public LinkedListNode removeAndMoveToFront(LinkedListNode reference) {
        LinkedListNode removedNode = null;
        if (head != null && reference != null) {

            //If it is the first node, nothing to be done
            if (reference.getPrevious() == null) {
                return reference;
            }

            //If it is the middle node , remove it
            if (reference.getPrevious() != null && reference.getNext() != null) {
                reference.getPrevious().setNext(reference.getNext());
                reference.getNext().setPrevious(reference.getPrevious());
                removedNode = reference;
            } else if (reference.getPrevious() != null && reference.getNext() == null) {
                reference.getPrevious().setNext(null);
                this.setTail(reference.getPrevious()); //Updating the tail for doubly linked list
                reference.setPrevious(null);
                removedNode = reference;
            }
        } else {
            return removedNode;
        }
        return removedNode;
    }

    public LinkedListNode getHead() {
        return head;
    }

    public void setHead(LinkedListNode head) {
        this.head = head;
    }

    public LinkedListNode getTail() {
        return tail;
    }

    public void setTail(LinkedListNode tail) {
        this.tail = tail;
    }
}
