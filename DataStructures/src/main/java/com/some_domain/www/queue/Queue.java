package com.some_domain.www.queue;

/**
 * @author : waniasra
 * @date : 10/13/2019 10:54 PM
 * This class demonstrates how to create a queue
 */
public class Queue {

    private Node front;
    private Node rear;
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public static void main(String[] args) {

        Queue queue = new Queue();

        //Inserting few nodes into the queue
        queue.enQueue(10);
        queue.enQueue(9);
        queue.enQueue(8);
        queue.enQueue(7);
        queue.enQueue(6);
        queue.enQueue(5);

        System.out.println("Current contents of the queue are as follows");
        queue.displayContentsOfQueue();
        System.out.println("\nCurrent size of the queue is [ " + queue.getSize() + " ]. Front [ " + queue.getFront().getData() + " ] . Rear [ " + queue.getRear().getData() + " ]");

        //Performing few deQueue operations now
        System.out.println();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        System.out.println("\nCurrent contents of the queue are as follows");
        queue.displayContentsOfQueue();
        System.out.println("\nCurrent size of the queue is [ " + queue.getSize() + " ]. Front [ " + queue.getFront().getData() + " ] . Rear [ " + queue.getRear().getData() + " ]");
    }

    public void enQueue(int data) {
        Node newNode = new Node(data, null);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public void deQueue() {
        if (front == null) {
            System.out.println("Queue is empty.");
        } else {
            Node deletedNode = front;
            front = front.getNext();
            size--;
            System.out.println("Node deleted from the front of the queue is [ " + deletedNode.getData() + " ]");
        }
    }

    public void displayContentsOfQueue() {
        if (front == null) {
            System.out.println("Queue is empty.");
        } else {
            Node traversingNode = front;
            while (traversingNode != null) {
                System.out.print(traversingNode.getData() + " ");
                traversingNode = traversingNode.getNext();
            }
        }
    }

    public Node getFront() {
        return front;
    }

    public void setFront(Node front) {
        this.front = front;
    }

    public Node getRear() {
        return rear;
    }

    public void setRear(Node rear) {
        this.rear = rear;
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
