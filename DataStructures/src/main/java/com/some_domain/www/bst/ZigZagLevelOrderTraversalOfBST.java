package com.some_domain.www.bst;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
        bst.printSpiralOrderTraversalOfBST(bst.getRoot());
        System.out.println("Time and space complexity for this approach is O(N) where N is the number of nodes in BST");
    }

    /*
        The idea is to use two stacks
     */
    public void printSpiralOrderTraversalOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
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
            return;
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
                        nodesAtCurrentLevel.addFirst(rootReference);
                    } else {
                        nodesAtCurrentLevel.addLast(rootReference);
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
