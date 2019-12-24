package com.some_domain.www.bst;

/**
 * @author : waniasra
 * @date : 10/18/2019 5:35 PM
 * This class demonstrates how to check if a given binary tree is a BST
 */

/**
 * A tree is a BST if it has following three properties:
 * 1. Root has value greater than or equal to all the nodes on its left subtree
 * 2. Root has value less than all the nodes on its right subtree
 * 3. Left subtree and right subtree are also binary search trees
 */
public class CheckIfTreeIsBST {

    public static void main(String[] args) {

        CheckIfTreeIsBST bst = new CheckIfTreeIsBST();

        Node treeNode = bst.createTree();
        boolean isBST = bst.isBST(treeNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Given tree is a BST : " + isBST);
        System.out.println("Time complexity is O(N)");
    }

    public boolean isBST(Node rootReference, int lowerBound, int upperBound) {
        if (rootReference == null)
            return true;
        if (rootReference.getData() < lowerBound || rootReference.getData() > upperBound)
            return false;
        //For left subtree Integer.MIN_VALUE is the lower bound and processing root node's data is upper bound
        //For right subtree, processing root node's data is the lower bound and Integer.MAX_VALUE is the upper bound
        return isBST(rootReference.getLeftChild(), lowerBound, rootReference.getData()) &&
                isBST(rootReference.getRightChild(), rootReference.getData(), upperBound);
    }

    public Node createTree() {

        /* Example:-
                                      20
                                    /    \
                                   15      25
                                 /  \      /  \
                                8    16   24   30
         */
        //Creating the above given given tree
        Node rootNode = new Node(20, null, null);
        rootNode.setLeftChild(new Node(15,
                new Node(8, null, null),
                new Node(16, null, null)));
        rootNode.setRightChild(new Node(25,
                new Node(24, null, null),
                new Node(30, null, null)));
        return rootNode;
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
