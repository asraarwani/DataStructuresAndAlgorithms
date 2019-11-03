package com.some_domain.www.binarytree;

/**
 * @author : waniasra
 * @date : 11/3/2019 10:58 AM
 * This class demonstrates how to find the maximum and minimum node from a given binary tree
 */
//Reference : https://www.geeksforgeeks.org/find-maximum-or-minimum-in-binary-tree/
public class MinAndMaxFromBinaryTree {

    private Node root;

    public MinAndMaxFromBinaryTree() {
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
                                       5
                                    /   \
                                   2       9
                                 /  \     /  \
                                5    6   7    3
         */


        MinAndMaxFromBinaryTree bst = new MinAndMaxFromBinaryTree();
        Node root = bst.createBinaryTree();
        bst.setRoot(root);

        int maximumNode = bst.findMaximumNode(bst.getRoot());
        System.out.println("Maximum node is : " + maximumNode);

        int minimumNode = bst.findMinimumNode(bst.getRoot());
        System.out.println("Minimum node is : " + minimumNode);
    }

    public int findMaximumNode(Node rootReference) {
        if (rootReference == null)
            return Integer.MIN_VALUE;

        int rootNode = rootReference.getData();
        int leftSubTreeMaximumNode = findMaximumNode(rootReference.getLeftChild());
        int rightSubTreeMaximumNode = findMaximumNode(rootReference.getRightChild());

        if (leftSubTreeMaximumNode > rootNode)
            rootNode = leftSubTreeMaximumNode;
        if (rightSubTreeMaximumNode > rootNode)
            rootNode = rightSubTreeMaximumNode;
        return rootNode;
    }

    public int findMinimumNode(Node rootReference) {
        if (rootReference == null)
            return Integer.MAX_VALUE;

        int rootNode = rootReference.getData();
        int leftSubTreeMinimumNode = findMinimumNode(rootReference.getLeftChild());
        int rightSubTreeMinimumNode = findMinimumNode(rootReference.getRightChild());

        if (leftSubTreeMinimumNode < rootNode)
            rootNode = leftSubTreeMinimumNode;
        if (rightSubTreeMinimumNode < rootNode)
            rootNode = rightSubTreeMinimumNode;
        return rootNode;
    }

    public Node createBinaryTree() {
        Node root = new Node(5, null, null);
        root.setLeftChild(new Node(2, new Node(5, null, null), new Node(6, null, null)));
        root.setRightChild(new Node(9, new Node(7, null, null), new Node(3, null, null)));
        return root;
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
