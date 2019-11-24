package com.some_domain.www.bst;

import java.util.Stack;

/**
 * @author : waniasra
 * @date : 11/1/2019 12:31 PM
 * This class demonstrates how to find all the paths from root to any leaf node with sum equal to K in a BST
 * Note : We are looking for paths starting from ROOT node and ending at some leaf node
 */
public class PrintAllKSumPathsFromRootToLeafInBST {

    private Node root;

    public PrintAllKSumPathsFromRootToLeafInBST() {
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

        PrintAllKSumPathsFromRootToLeafInBST bst = new PrintAllKSumPathsFromRootToLeafInBST();

        Node rootReference = bst.insertNodeIntoBSTRecursively(null, 20);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 15);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 25);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 8);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 16);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 24);
        rootReference = bst.insertNodeIntoBSTRecursively(rootReference, 30);
        bst.setRoot(rootReference);

        int kSum = 51;
        bst.printAllKSumPathsFromBST(bst.getRoot(), kSum, new Stack(), 0);
        System.out.println("Time complexity is O(N), as we are using pre-order traversal of BST approach");
    }

    public void printAllKSumPathsFromBST(Node rootReference, int kSum, Stack<Node> stack, int sumSoFar) {
        if (rootReference == null)
            return;

        sumSoFar = sumSoFar + rootReference.getData();
        stack.push(rootReference);

        if (sumSoFar == kSum && rootReference.getLeftChild() == null && rootReference.getRightChild() == null) {
            System.out.println("We are looking for kSum [ " + kSum + " ]");
            System.out.println(stack);
            sumSoFar = 0;
        }
        printAllKSumPathsFromBST(rootReference.getLeftChild(), kSum, stack, sumSoFar);

        printAllKSumPathsFromBST(rootReference.getRightChild(), kSum, stack, sumSoFar);

        stack.pop();
    }

    public Node insertNodeIntoBSTRecursively(Node rootReference, int data) {
        if (rootReference == null) {
            rootReference = new Node(data, null, null);
        } else if (data <= rootReference.getData()) {
            rootReference.setLeftChild(insertNodeIntoBSTRecursively(rootReference.getLeftChild(), data));
        } else {
            rootReference.setRightChild(insertNodeIntoBSTRecursively(rootReference.getRightChild(), data));
        }
        return rootReference;
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

        public void setRightChild(Node righChild) {
            this.rightChild = righChild;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }
}
