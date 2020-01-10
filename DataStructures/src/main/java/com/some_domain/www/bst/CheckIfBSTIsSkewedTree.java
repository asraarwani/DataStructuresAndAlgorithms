package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 1/10/2020 10:55 AM
 * This class demonstrates how to check if a given BST is a skewed BST
 */
public class CheckIfBSTIsSkewedTree {

    private Node root;

    public CheckIfBSTIsSkewedTree() {
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
                                     /
                                   15
                                  /
                                 8
                               /
                              2
         */

        CheckIfBSTIsSkewedTree bst = new CheckIfBSTIsSkewedTree();
        bst.insertNodeIntoBSTIteratively(20);
        bst.insertNodeIntoBSTIteratively(15);
        bst.insertNodeIntoBSTIteratively(8);
        bst.insertNodeIntoBSTIteratively(2);
        // bst.insertNodeIntoBSTItearatively(30);

        bst.printInOrderTraversalOfBST(bst.getRoot());

        System.out.println();
        boolean isSkewedBST = bst.isSkewedBST(bst.getRoot());
        if (isSkewedBST) {
            System.out.println("BST is a skewed BST");
            System.out.println("Time complexity is O(H) where H is the height of the BST");
        } else {
            System.out.println("BST is not a skewed BST");
        }
    }

    public boolean isSkewedBST(Node rootReference) {
        if (rootReference == null || (rootReference.getLeftChild() == null && rootReference.getRightChild() == null))
            return true;
        if (rootReference.getLeftChild() != null && rootReference.getRightChild() != null)
            return false;
        if (rootReference.getLeftChild() != null)
            return isSkewedBST(rootReference.getLeftChild());
        if (rootReference.getRightChild() != null)
            return isSkewedBST(rootReference.getRightChild());
        return false;
    }

    public void printInOrderTraversalOfBST(Node rootReference) {
        if (rootReference == null) {
            System.out.println("BST is empty");
            return;
        } else {
            Stack<Node> stack = new Stack<>();
            while (true) {

                while (rootReference != null) {
                    stack.push(rootReference);
                    rootReference = rootReference.getLeftChild();
                }

                if (stack.isEmpty())
                    break;

                rootReference = stack.pop();
                System.out.print(rootReference.getData() + " ");
                rootReference = rootReference.getRightChild();
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
