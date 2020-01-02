package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 11/27/2019 10:22 PM
 * This class demonstrates how to print the common nodes (Intersection) between two BSTs
 */
//Reference : https://www.geeksforgeeks.org/print-common-nodes-in-two-binary-search-trees/
public class PrintCommonNodesInTwoBSTs {

    private Node root;

    public PrintCommonNodesInTwoBSTs() {
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

        PrintCommonNodesInTwoBSTs bst = new PrintCommonNodesInTwoBSTs();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(25);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(16);
        bst.insertNodeIntoBSTIteratively(24);
        bst.insertNodeIntoBSTIteratively(30);

            /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                1    16   24   35
         */

        PrintCommonNodesInTwoBSTs bst2 = new PrintCommonNodesInTwoBSTs();
        bst2.insertNodeIntoBSTIteratively(20);
        bst2.insertNodeIntoBSTIteratively(15);
        bst2.insertNodeIntoBSTIteratively(25);
        bst2.insertNodeIntoBSTIteratively(1);
        bst2.insertNodeIntoBSTIteratively(16);
        bst2.insertNodeIntoBSTIteratively(24);
        bst2.insertNodeIntoBSTIteratively(35);

        System.out.println("Common nodes in two BST are given as follows");
        new PrintCommonNodesInTwoBSTs().printCommonNodesFromTwoBSTs(bst.getRoot(), bst2.getRoot());

        System.out.println("\nTime and space complexity is O(M + N) where M and N are the number of nodes in two BSTs");
    }

    /*
        The idea is to use in-order traversal simultaneously on both the BSTs
     */
    public void printCommonNodesFromTwoBSTs(Node firstBSTRootRef, Node secondBSTRootRef) {
        if (firstBSTRootRef == null || secondBSTRootRef == null) {
            System.out.println("There are no common nodes in two BSTs");
            return;
        } else {
            Stack<Node> firstStack = new Stack<>();
            Stack<Node> secondStack = new Stack<>();
            while (true) {

                while (firstBSTRootRef != null) {
                    firstStack.push(firstBSTRootRef);
                    firstBSTRootRef = firstBSTRootRef.getLeftChild();
                }

                while (secondBSTRootRef != null) {
                    secondStack.push(secondBSTRootRef);
                    secondBSTRootRef = secondBSTRootRef.getLeftChild();
                }

                if (firstStack.isEmpty() || secondStack.isEmpty()) {
                    break;
                }

                firstBSTRootRef = firstStack.peek();
                secondBSTRootRef = secondStack.peek();

                if (firstBSTRootRef.getData() == secondBSTRootRef.getData()) {
                    System.out.print(firstBSTRootRef.getData() + " ");
                    firstStack.pop();
                    secondStack.pop();
                    firstBSTRootRef = firstBSTRootRef.getRightChild();
                    secondBSTRootRef = secondBSTRootRef.getRightChild();
                } else if (firstBSTRootRef.getData() < secondBSTRootRef.getData()) {
                    firstStack.pop();
                    firstBSTRootRef = firstBSTRootRef.getRightChild();
                    secondBSTRootRef = null;
                } else {
                    secondStack.pop();
                    secondBSTRootRef = secondBSTRootRef.getRightChild();
                    firstBSTRootRef = null;
                }
            }
        }
    }

    public void insertNodeIntoBSTIteratively(int data) {
        Node newNode = new Node(data, null, null);
        if (root == null) {
            root = newNode;
        } else {
            Node processingNodeParent = null;
            Node traversingNode = root;
            while (true) {
                processingNodeParent = traversingNode;
                if (data <= traversingNode.getData()) {
                    traversingNode = traversingNode.getLeftChild();
                    if (traversingNode == null) {
                        processingNodeParent.setLeftChild(newNode);
                        break;
                    }
                } else {
                    traversingNode = traversingNode.getRightChild();
                    if (traversingNode == null) {
                        processingNodeParent.setRightChild(newNode);
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
