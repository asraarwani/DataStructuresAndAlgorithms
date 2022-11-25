package com.some_domain.www.stack;

/**
 * @author : asraar
 * @date : 25-11-2022 02:15 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/queue-using-stacks/
 */
public class QueueUsingTwoStacks {

    private Node top;
    private int size;

    public static void main(String[] args) {

        QueueUsingTwoStacks firstStack = new QueueUsingTwoStacks();
        firstStack.push(5);
        firstStack.push(4);
        firstStack.push(3);
        firstStack.push(2);
        firstStack.push(1);

        firstStack.displayContentsOfStack(firstStack.getTop());

        QueueUsingTwoStacks secondStack = new QueueUsingTwoStacks();

        System.out.println();
        firstStack.enqueueUsingTwoStacks(100, firstStack, secondStack);
        System.out.println();
        firstStack.displayContentsOfStack(firstStack.getTop());

        System.out.println();

        firstStack.dequeueUsingTwoStacks(firstStack);
        firstStack.dequeueUsingTwoStacks(firstStack);
        firstStack.dequeueUsingTwoStacks(firstStack);
        firstStack.enqueueUsingTwoStacks(200, firstStack, secondStack);

        System.out.println();
        firstStack.displayContentsOfStack(firstStack.getTop());

        System.out.println("\nTime complexity for enqueue operation is O(N) and for dequeue operation it is O(1)");

    }

    private void enqueueUsingTwoStacks(int data, QueueUsingTwoStacks firstStack, QueueUsingTwoStacks secondStack) {
        //Move all items from stack 1 to stack 2
        if (firstStack.getTop() != null) {
            while (firstStack.peek() != null) {
                secondStack.push(firstStack.pop().getData());
            }
        }

        //Push new item into stack 1
        firstStack.push(data);

        //Move back all items from stack 2 to stack 1
        if (secondStack.getTop() != null) {
            while (secondStack.peek() != null) {
                firstStack.push(secondStack.pop().getData());
            }
        }
    }

    private void dequeueUsingTwoStacks(QueueUsingTwoStacks firstStack) {
        if (firstStack.top == null) {
            System.out.println("Stack is empty");
        } else {
            firstStack.pop();
        }
    }

    private void push(int data) {
        Node newNode = new Node(data, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
        size++;
    }

    private Node pop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return null;
        } else {
            Node poppedNode = top;
            top = top.getNext();
            size--;
            return poppedNode;
        }
    }

    public Node peek() {
        if (top == null) {
            return null;
        } else {
            return top;
        }
    }

    private void displayContentsOfStack(Node topReference) {
        if (topReference == null) {
            System.out.println("Stack is empty");
        } else {
            Node currentNode = topReference;
            while (currentNode != null) {
                System.out.print(currentNode.getData() + " ");
                currentNode = currentNode.getNext();
            }
        }
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

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
