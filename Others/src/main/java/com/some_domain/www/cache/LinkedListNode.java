package com.some_domain.www.cache;

public class LinkedListNode {
    private int key;
    private int value;
    private LinkedListNode next;
    private LinkedListNode previous;

    public LinkedListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedListNode previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "LinkedListNode{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
