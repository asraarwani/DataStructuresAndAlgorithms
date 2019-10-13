package com.some_domain.www.stack;

/**
 * @author : waniasra
 * @date : 10/13/2019 10:37 PM
 * This class demonstrates how to create a stack
 */
public class Stack {

    private Node top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        Stack stack = new Stack();

        //Inserting few nodes into stack
        stack.push(10);
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);

        System.out.println("Contents of the stack are");
        stack.displayContentsOfStack();

        System.out.println("\nSize of the stack is [ " + stack.getSize() + " ]. Top node is [ " + stack.getTop().getData() + "]");

        Node peekNode = stack.peek();
        if (peekNode != null)
            System.out.println("\nPeek node in stack is [ " + peekNode.getData() + " ]");


        //Popping few nodes from the top of the stack now
        Node poppedNode = stack.pop();
        if (poppedNode != null)
            System.out.println("\nPopped node from stack [ " + poppedNode.getData() + " ]");
        poppedNode = stack.pop();
        if (poppedNode != null)
            System.out.println("\nPopped node from stack [ " + poppedNode.getData() + " ]");


        System.out.println("\nContents of the stack are");
        stack.displayContentsOfStack();

        System.out.println("\nSize of the stack is [ " + stack.getSize() + " ]. Top node is [ " + stack.getTop().getData() + "]");
    }

    public void push(int data) {
        Node newNode = new Node(data, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
        size++;
    }

    public Node peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        } else {
            return top;
        }
    }

    public Node pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        } else {
            Node poppedNode = top;
            top = top.getNext();
            size--;
            return poppedNode;
        }
    }

    public void displayContentsOfStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            Node traversingNode = top;
            while (traversingNode != null) {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getNext();
            }
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
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

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
