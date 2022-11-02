package com.some_domain.www.bst;

import java.util.*;

/**
 * @author : waniasra
 * @date : 1/6/2020 9:22 PM
 * This class demonstrates how to print BST in zig-zag order (spiral order)
 */
//Reference : https://medium.com/@harycane/binary-tree-zigzag-level-order-traversal-5b96a3e1b767
//Reference : https://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/   -> Iterative approach
public class ZigZagLevelOrderTraversalOfBST {

    private Node root;

    public ZigZagLevelOrderTraversalOfBST() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {

       /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */

        ZigZagLevelOrderTraversalOfBST bst = new ZigZagLevelOrderTraversalOfBST();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

        bst.printLevelOrderTraversalInZigZag(bst.getRoot());
        System.out.println("Time and space complexity is O(N) where N is the number of nodes in the BST");
        System.out.println("We're using LinkedList's addFirst and addLast methods, TC is O(N)");

        System.out.println();
        bst.printSpiralOrderTraversalOfBSTUsingTwoStacks(bst.getRoot());
        System.out.println("Time and space complexity for this approach is O(N) where N is the number of nodes in BST");

        System.out.println();
        bst.printSpiralOrderTraversalOfBSTUsingDequeue(bst.getRoot());
        System.out.println("Time and space complexity for this approach is O(N) where N is the number of nodes in BST");

        System.out.println();
        bst.printLevelOrderTraversalInZigZagFashionUsingLinkedList(bst.getRoot());
        System.out.println("Time and space complexity for this approach is O(N) where N is the number of nodes in BST");
    }

    /*
        The idea is to use dequeue
     */
    private void printSpiralOrderTraversalOfBSTUsingDequeue(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
        } else {
            Deque<Node> deque = new LinkedList<>();
            deque.offer(rootReference);
            boolean flag = true;
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    Node polledNode = null;
                    if (flag) {
                        polledNode = deque.pollLast();
                    } else {
                        polledNode = deque.poll();
                    }
                    System.out.print(polledNode.getData() + " ");
                    if (polledNode.getLeftChild() != null) {
                        deque.offer(polledNode.getLeftChild());
                    }

                    if (polledNode.getRightChild() != null) {
                        deque.offer(polledNode.getRightChild());
                    }
                }
                flag = !flag;
                System.out.println();
            }
        }
    }

    /*
        The idea is to use two stacks
     */
    public void printSpiralOrderTraversalOfBSTUsingTwoStacks(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
        } else {
            Stack<Node> firstStack = new Stack<>();
            Stack<Node> secondStack = new Stack<>();
            firstStack.push(rootReference);
            while (!firstStack.isEmpty() || !secondStack.isEmpty()) {

                System.out.println();
                while (!firstStack.isEmpty()) {
                    Node polledNode = firstStack.pop();
                    System.out.print(polledNode.getData() + " ");
                    if (polledNode.getRightChild() != null) {
                        secondStack.push(polledNode.getRightChild());
                    }
                    if (polledNode.getLeftChild() != null) {
                        secondStack.push(polledNode.getLeftChild());
                    }
                }

                System.out.println();
                while (!secondStack.isEmpty()) {
                    Node polledNode = secondStack.pop();
                    System.out.print(polledNode.getData() + " ");
                    if (polledNode.getLeftChild() != null) {
                        firstStack.push(polledNode.getLeftChild());
                    }
                    if (polledNode.getRightChild() != null) {
                        firstStack.push(polledNode.getRightChild());
                    }
                }
            }
        }
    }

    /*
        Note: we are using addFirst and addLast methods of LinkedList class, and TC for these operations is O(N)
     */
    public void printLevelOrderTraversalInZigZag(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
        } else {
            List<LinkedList<Node>> resultingList = new LinkedList<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(rootReference);
            boolean zigZag = false;
            while (!queue.isEmpty()) {
                LinkedList<Node> nodesAtCurrentLevel = new LinkedList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    rootReference = queue.poll();
                    if (zigZag) {
                        nodesAtCurrentLevel.addLast(rootReference);
                    } else {
                        nodesAtCurrentLevel.addFirst(rootReference);
                    }

                    //If polled node has a left child
                    if (rootReference.getLeftChild() != null) {
                        queue.offer(rootReference.getLeftChild());
                    }

                    //If polled node has a right child
                    if (rootReference.getRightChild() != null) {
                        queue.offer(rootReference.getRightChild());
                    }
                }
                zigZag = !zigZag;
                resultingList.add(nodesAtCurrentLevel);
            }
            resultingList.stream().forEach(list -> {
                list.stream().forEach(node -> System.out.print(node.getData() + " "));
                System.out.println();
            });
        }
    }

    public void printLevelOrderTraversalInZigZagFashionUsingLinkedList(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
        } else {
            LinkedList<Node> linkedList = new LinkedList<>();
            linkedList.add(rootReference);
            boolean zigZag = false;
            while (linkedList.size() > 0) {
                int size = linkedList.size();
                for (int i = 0; i < size; i++) {
                    Node removedNode = null;
                    if (zigZag) {
                        removedNode = linkedList.removeFirst();
                    } else {
                        removedNode = linkedList.removeLast();
                    }
                    System.out.print(removedNode.getData() + " ");

                    //If polled node has a left child
                    if (removedNode.getLeftChild() != null) {
                        linkedList.add(removedNode.getLeftChild());
                    }
                    //If polled node has a right child
                    if (removedNode.getRightChild() != null) {
                        linkedList.add(removedNode.getRightChild());
                    }
                }
                zigZag = !zigZag;
                System.out.println();
            }
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node parentOfCurrentNode = null;
            Node currentNode = root;
            while (true) {
                parentOfCurrentNode = currentNode;
                if (data <= currentNode.getData()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        parentOfCurrentNode.setLeftChild(newNode);
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentOfCurrentNode.setRightChild(newNode);
                        break;
                    }
                }
            }
        }
    }

    private class Node {

        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int data, Node leftChild, Node rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}
